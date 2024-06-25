package com.cafe.elasticsearch.message;

import com.cafe.common.constant.app.FieldConstant;
import com.cafe.common.constant.model.GoodsConstant;
import com.cafe.common.constant.rocketmq.RocketMQConstant;
import com.cafe.common.util.json.JacksonUtil;
import com.cafe.elasticsearch.model.Goods;
import com.cafe.elasticsearch.service.GoodsService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.message
 * @Author: zhouboyi
 * @Date: 2022/11/3 15:24
 * @Description: RocketMQ 商品消息消费者
 */
@Component
@RocketMQMessageListener(topic = RocketMQConstant.Topic.GOODS, consumerGroup = RocketMQConstant.ConsumerGroup.ELASTICSEARCH)
public class RocketMQGoodsConsumer implements RocketMQListener<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RocketMQGoodsConsumer.class);

    private final GoodsService goodsService;

    @Autowired
    public RocketMQGoodsConsumer(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @Override
    public void onMessage(String message) {
        // 打印成功接收消息的日志
        LOGGER.info("RocketMQGoodsConsumer.onMessage(): rocketmq message -> {}", message);
        // 获取消息内容
        Map<String, Object> content = JacksonUtil.readValue(message, new TypeReference<Map<String, Object>>() {});
        // 获取上下架标识
        Integer status = Integer.parseInt(String.valueOf(content.get(FieldConstant.STATUS)));

        try {
            if (GoodsConstant.Status.LAUNCH.equals(status)) {
                // 获取上架商品的信息
                List<Goods> goodsList = JacksonUtil.convertValue(content.get(FieldConstant.DATA), new TypeReference<List<Goods>>() {});
                // 上架商品
                goodsService.insertBatch(goodsList);
                // 打印成功上架商品的日志
                LOGGER.info("RocketMQGoodsConsumer.onMessage(): Put away goods success! rocketmq message -> {}", message);
            } else {
                // 获取下架商品的主键
                List<String> ids = JacksonUtil.convertValue(content.get(FieldConstant.DATA), new TypeReference<List<String>>() {});
                // 下架商品
                goodsService.deleteBatch(ids);
                // 打印成功下架商品的日志
                LOGGER.info("RocketMQGoodsConsumer.onMessage(): Sold out goods success! rocketmq message -> {}", message);
            }
        } catch (IOException e) {
            LOGGER.error("RocketMQGoodsConsumer.onMessage(): Failed to launch goods! message -> {}", e.getMessage(), e);
        }
    }
}
