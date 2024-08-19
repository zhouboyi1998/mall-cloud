package com.cafe.elasticsearch.message.rocketmq;

import com.cafe.common.constant.app.FieldConstant;
import com.cafe.common.constant.model.GoodsConstant;
import com.cafe.common.constant.rocketmq.RocketMQConstant;
import com.cafe.common.util.json.JacksonUtil;
import com.cafe.elasticsearch.index.GoodsIndex;
import com.cafe.elasticsearch.service.GoodsIndexService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.message.rocketmq
 * @Author: zhouboyi
 * @Date: 2022/11/3 15:24
 * @Description: RocketMQ 商品消息消费者
 */
@Component
@RocketMQMessageListener(topic = RocketMQConstant.Topic.GOODS_INDEX, consumerGroup = RocketMQConstant.ConsumerGroup.ELASTICSEARCH)
public class GoodsConsumer implements RocketMQListener<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsConsumer.class);

    private final GoodsIndexService goodsIndexService;

    @Autowired
    public GoodsConsumer(GoodsIndexService goodsIndexService) {
        this.goodsIndexService = goodsIndexService;
    }

    @Override
    public void onMessage(String message) {
        // 打印成功接收消息的日志
        LOGGER.info("GoodsConsumer.onMessage(): rocketmq message -> {}", message);
        // 获取消息内容
        Map<String, Object> content = JacksonUtil.readValue(message, new TypeReference<Map<String, Object>>() {});
        // 获取上下架标识
        Integer status = Integer.parseInt(String.valueOf(content.get(FieldConstant.STATUS)));

        if (GoodsConstant.Status.LAUNCH.equals(status)) {
            // 获取上架商品的信息
            List<GoodsIndex> goodsIndexList = JacksonUtil.convertValue(content.get(FieldConstant.DATA), new TypeReference<List<GoodsIndex>>() {});
            // 上架商品
            goodsIndexService.insertBatch(goodsIndexList);
            // 打印成功上架商品的日志
            LOGGER.info("GoodsConsumer.onMessage(): Put away goods success! rocketmq message -> {}", message);
        } else {
            // 获取下架商品的主键
            List<String> ids = JacksonUtil.convertValue(content.get(FieldConstant.DATA), new TypeReference<List<String>>() {});
            // 下架商品
            goodsIndexService.deleteBatch(ids);
            // 打印成功下架商品的日志
            LOGGER.info("GoodsConsumer.onMessage(): Sold out goods success! rocketmq message -> {}", message);
        }
    }
}
