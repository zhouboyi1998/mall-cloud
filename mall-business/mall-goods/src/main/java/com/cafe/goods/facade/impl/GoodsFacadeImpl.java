package com.cafe.goods.facade.impl;

import com.cafe.common.constant.app.FieldConstant;
import com.cafe.common.constant.model.GoodsConstant;
import com.cafe.common.constant.rocketmq.RocketMQConstant;
import com.cafe.common.jackson.util.JacksonUtil;
import com.cafe.goods.facade.GoodsFacade;
import com.cafe.goods.model.bo.Goods;
import com.cafe.goods.service.GoodsService;
import com.cafe.goods.service.SkuService;
import com.cafe.infrastructure.rocketmq.producer.RocketMQProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.facade.impl
 * @Author: zhouboyi
 * @Date: 2024/12/3 17:59
 * @Description: 商品防腐层实现类
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class GoodsFacadeImpl implements GoodsFacade {

    private final GoodsService goodsService;

    private final SkuService skuService;

    private final RocketMQProducer rocketMQProducer;

    @Override
    public void launch(List<Long> ids, Integer status) {
        // 更新商品状态
        Integer count = skuService.updateStatus(ids, status);

        // 组装 RocketMQ 消息内容
        Map<String, Object> content = new HashMap<>(2);
        // 消息标识: 标识消息是上架通知还是下架通知
        content.put(FieldConstant.STATUS, status);
        // 消息内容
        if (GoodsConstant.Status.LAUNCH.equals(status)) {
            // 查询上架商品的信息
            List<Goods> goodsList = goodsService.list(ids);
            // 将上架商品的信息组装进 RocketMQ 消息内容中
            content.put(FieldConstant.DATA, goodsList);
        } else {
            // 将下架商品的主键组装进 RocketMQ 消息内容中
            content.put(FieldConstant.DATA, ids);
        }

        // 发送消息到 RocketMQ, 通知 ElasticSearch 上下架商品
        rocketMQProducer.convertAndSend(RocketMQConstant.Topic.GOODS_INDEX, content);
        // 打印日志
        log.info("GoodsFacadeImpl.launch(): status -> {}, count -> {}, rocketmq message -> {}", status, count, JacksonUtil.writeValueAsString(content));
    }
}
