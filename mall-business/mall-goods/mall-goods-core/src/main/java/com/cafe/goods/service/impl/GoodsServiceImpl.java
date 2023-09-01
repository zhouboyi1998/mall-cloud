package com.cafe.goods.service.impl;

import com.cafe.common.constant.app.FieldConstant;
import com.cafe.common.constant.model.GoodsConstant;
import com.cafe.common.constant.rocketmq.RocketMQConstant;
import com.cafe.common.message.rocketmq.producer.RocketMQProducer;
import com.cafe.common.redis.annotation.ResultCache;
import com.cafe.common.util.json.JacksonUtil;
import com.cafe.goods.bo.Goods;
import com.cafe.goods.mapper.GoodsMapper;
import com.cafe.goods.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.service.impl
 * @Author: zhouboyi
 * @Date: 2022/10/11 11:36
 * @Description:
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsServiceImpl.class);

    private final GoodsMapper goodsMapper;

    private final RocketMQProducer rocketMQProducer;

    @Autowired
    public GoodsServiceImpl(GoodsMapper goodsMapper, RocketMQProducer rocketMQProducer) {
        this.goodsMapper = goodsMapper;
        this.rocketMQProducer = rocketMQProducer;
    }

    @ResultCache
    @Override
    public List<Goods> list() {
        return goodsMapper.list(null);
    }

    @Override
    public void launch(List<Long> ids, Integer status) {
        // 更新商品状态
        goodsMapper.updateStatus(ids, status);

        // 组装 RocketMQ 消息内容
        Map<String, Object> content = new HashMap<>(2);
        // 消息标识: 标识消息是上架通知还是下架通知
        content.put(FieldConstant.STATUS, status);
        // 消息内容
        if (GoodsConstant.Status.LAUNCH.equals(status)) {
            // 查询上架商品的信息
            List<Goods> goodsList = goodsMapper.list(ids);
            // 将上架商品的信息组装进 RocketMQ 消息内容中
            content.put(FieldConstant.DATA, goodsList);
        } else {
            // 将下架商品的主键组装进 RocketMQ 消息内容中
            content.put(FieldConstant.DATA, ids);
        }

        // 发送消息到 RocketMQ, 通知 ElasticSearch 上下架商品
        rocketMQProducer.convertAndSend(RocketMQConstant.Topic.CANAL_TO_GOODS, content);
        // 打印日志
        LOGGER.info("GoodsServiceImpl.launch(): rocketmq message -> {}", JacksonUtil.writeValueAsString(content));
    }
}
