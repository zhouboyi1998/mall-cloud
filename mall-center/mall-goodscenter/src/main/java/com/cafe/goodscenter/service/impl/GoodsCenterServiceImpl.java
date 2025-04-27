package com.cafe.goodscenter.service.impl;

import com.cafe.common.constant.app.FieldConstant;
import com.cafe.common.constant.model.GoodsConstant;
import com.cafe.common.constant.rocketmq.RocketMQConstant;
import com.cafe.common.enumeration.http.HttpStatusEnum;
import com.cafe.common.util.builder.ToStringStyleHolder;
import com.cafe.elasticsearch.feign.GoodsIndexFeign;
import com.cafe.elasticsearch.model.index.GoodsIndex;
import com.cafe.goods.feign.GoodsFeign;
import com.cafe.goods.feign.SkuFeign;
import com.cafe.goods.feign.SpuFeign;
import com.cafe.goods.model.bo.Goods;
import com.cafe.goods.model.vo.SpuVO;
import com.cafe.goodscenter.model.converter.GoodsConverter;
import com.cafe.goodscenter.model.vo.GoodsDetail;
import com.cafe.goodscenter.model.vo.GoodsSummary;
import com.cafe.goodscenter.service.GoodsCenterService;
import com.cafe.infrastructure.elasticsearch.model.converter.PageConverter;
import com.cafe.infrastructure.elasticsearch.model.vo.AggregatedPageVO;
import com.cafe.infrastructure.rocketmq.producer.RocketMQProducer;
import com.cafe.merchant.feign.ShopFeign;
import com.cafe.merchant.model.entity.Shop;
import com.cafe.starter.boot.model.exception.BusinessException;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.transaction.Propagation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goodscenter.service.impl
 * @Author: zhouboyi
 * @Date: 2024/7/31 23:18
 * @Description: 商品中心业务实现类
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class GoodsCenterServiceImpl implements GoodsCenterService {

    private final GoodsIndexFeign goodsIndexFeign;

    private final GoodsFeign goodsFeign;

    private final SpuFeign spuFeign;

    private final SkuFeign skuFeign;

    private final ShopFeign shopFeign;

    private final RocketMQProducer rocketMQProducer;

    @GlobalTransactional(
        propagation = Propagation.REQUIRED,
        rollbackFor = Exception.class,
        timeoutMills = 120000,
        lockRetryInterval = 10
    )
    @Override
    public void shelve(Integer status, List<Long> skuIds) {
        // 更新数据库中的商品状态
        Integer count = Optional.ofNullable(skuFeign.updateStatus(status, skuIds))
            .map(ResponseEntity::getBody)
            .orElseThrow(() -> new BusinessException(HttpStatusEnum.UPDATE_GOODS_STATUS_FAIL, skuIds));
        // 上下架商品
        if (GoodsConstant.Status.ON_SHELVE.equals(status)) {
            // 批量上架商品
            onShelve(skuIds);
        } else if (GoodsConstant.Status.OFF_SHELVE.equals(status)) {
            // 批量下架商品
            offShelve(skuIds);
        } else {
            // 不支持的商品状态
            log.error("GoodsCenterServiceImpl.shelve(): status [{}] is invalid!", status);
        }
        // 打印处理条数日志
        log.info("GoodsCenterServiceImpl.shelve(): status -> {}, count -> {}", status, count);
    }

    private void onShelve(List<Long> skuIds) {
        // 查询上架的商品列表
        List<Goods> goodsList = Optional.ofNullable(goodsFeign.list(GoodsConstant.QueryType.INDEX, skuIds))
            .map(ResponseEntity::getBody)
            .orElseThrow(() -> new BusinessException(HttpStatusEnum.PENDING_ON_SHELVE_GOODS_NOT_FOUND, skuIds));
        // 转换成商品索引列表
        List<GoodsIndex> goodsIndexList = GoodsConverter.INSTANCE.toIndexList(goodsList);

        // 获取店铺id列表
        List<Long> shopIds = goodsList.stream().map(Goods::getShopId).collect(Collectors.toList());
        // 查询商品的店铺
        List<Shop> shopList = Optional.ofNullable(shopFeign.listByIds(shopIds))
            .map(ResponseEntity::getBody)
            .orElseThrow(() -> new BusinessException(HttpStatusEnum.SHOP_NOT_FOUND, shopIds));
        Map<Long, String> shopMap = shopList.stream().collect(Collectors.toMap(Shop::getId, Shop::getShopName));

        // TODO 查询商品的销量

        // TODO 查询商品的评论数

        // 遍历处理上架的商品
        goodsIndexList.forEach(goodsIndex -> {
            // 组装扩展数据
            goodsIndex.setShopName(shopMap.get(goodsIndex.getShopId()));
            // 将上架商品的信息组装进 RocketMQ 消息内容中
            Map<String, Object> content = new HashMap<>(2);
            content.put(FieldConstant.STATUS, GoodsConstant.Status.ON_SHELVE);
            content.put(FieldConstant.DATA, goodsIndex);
            // 发送 RocketMQ 消息
            rocketMQProducer.asyncSendOrderly(RocketMQConstant.Topic.GOODS_INDEX, content, String.valueOf(goodsIndex.getId()));
            log.info("GoodsCenterServiceImpl.onShelve(): content -> {}", ToStringBuilder.reflectionToString(content, ToStringStyleHolder.JSON_STYLE_WITHOUT_UNICODE));
        });
    }

    private void offShelve(List<Long> skuIds) {
        // 遍历处理下架的商品
        skuIds.forEach(skuId -> {
            // 将下架商品的主键组装进 RocketMQ 消息内容中
            Map<String, Object> content = new HashMap<>(2);
            content.put(FieldConstant.STATUS, GoodsConstant.Status.OFF_SHELVE);
            content.put(FieldConstant.DATA, skuId);
            // 发送 RocketMQ 消息
            rocketMQProducer.asyncSendOrderly(RocketMQConstant.Topic.GOODS_INDEX, content, String.valueOf(skuId));
            log.info("GoodsCenterServiceImpl.offShelve(): content -> {}", ToStringBuilder.reflectionToString(content, ToStringStyleHolder.JSON_STYLE_WITHOUT_UNICODE));
        });
    }

    @Override
    public AggregatedPageVO<GoodsSummary> summary(Integer current, Integer size, String sortField, String sortRule, String keyword) {
        // 使用关键词搜索商品索引列表
        AggregatedPageVO<GoodsIndex> goodsIndexPage = Optional.ofNullable(goodsIndexFeign.search(current, size, sortField, sortRule, keyword))
            .map(ResponseEntity::getBody)
            .orElse(null);
        // 如果没有搜索到商品索引, 返回空分页VO
        if (Objects.isNull(goodsIndexPage)) {
            return new AggregatedPageVO<>();
        }
        List<GoodsIndex> goodsIndexList = goodsIndexPage.getContent();
        List<Long> skuIds = goodsIndexList.stream().map(GoodsIndex::getId).collect(Collectors.toList());
        // 查询商品列表
        List<Goods> goodsList = Optional.ofNullable(goodsFeign.list(GoodsConstant.QueryType.SUMMARY, skuIds))
            .map(ResponseEntity::getBody)
            .orElseThrow(() -> new BusinessException(HttpStatusEnum.GOODS_SUMMARY_NOT_FOUND, skuIds));
        // 转换成商品概要列表
        List<GoodsSummary> goodsSummaryList = GoodsConverter.INSTANCE.toSummaryList(goodsList);
        return PageConverter.INSTANCE.convertAggregatedPageVOType(goodsIndexPage, new AggregatedPageVO<GoodsSummary>())
            .setContent(goodsSummaryList);
    }

    @Override
    public GoodsDetail detail(Long skuId) {
        // 根据 SKU id 查询 SPU 视图模型
        SpuVO spuVO = Optional.ofNullable(spuFeign.vo(skuId))
            .map(ResponseEntity::getBody)
            .orElseThrow(() -> new BusinessException(HttpStatusEnum.GOODS_DETAIL_NOT_FOUND, skuId));
        // 转换成商品详情并返回
        return GoodsConverter.INSTANCE.toDetail(spuVO);
    }
}
