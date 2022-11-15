package com.cafe.search.elasticsearch.message;

import cn.hutool.json.JSONUtil;
import com.cafe.common.constant.MessageConstant;
import com.cafe.common.message.rocketmq.constant.RocketMQConsumerGroup;
import com.cafe.common.message.rocketmq.constant.RocketMQTopic;
import com.cafe.search.elasticsearch.model.Goods;
import com.cafe.search.elasticsearch.service.ElasticSearchGoodsService;
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
 * @Package: com.cafe.search.elasticsearch.message
 * @Author: zhouboyi
 * @Date: 2022/11/3 15:24
 * @Description:
 */
@Component
@RocketMQMessageListener(consumerGroup = RocketMQConsumerGroup.GROUP_GOODS, topic = RocketMQTopic.CANAL_TO_ES_GOODS)
public class RocketMQGoodsConsumer implements RocketMQListener<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RocketMQGoodsConsumer.class);

    private final ElasticSearchGoodsService elasticSearchGoodsService;

    @Autowired
    public RocketMQGoodsConsumer(ElasticSearchGoodsService elasticSearchGoodsService) {
        this.elasticSearchGoodsService = elasticSearchGoodsService;
    }

    @Override
    public void onMessage(String message) {
        // 打印成功接收消息的日志
        LOGGER.info("RocketMQGoodsConsumer.onMessage(): Goods JSON Message -> {}", message);
        // 获取消息内容
        Map<String, Object> content = JSONUtil.parseObj(message);
        // 获取上下架标识
        Integer status = Integer.parseInt(String.valueOf(content.get(MessageConstant.STATUS)));

        try {
            if (MessageConstant.LAUNCH.equals(status)) {
                // 获取上架商品的信息
                List<Goods> goodsList = JSONUtil.parseArray(content.get(MessageConstant.DATA)).toList(Goods.class);
                // 上架商品
                elasticSearchGoodsService.insertBatch(goodsList);
                // 打印成功上架商品的日志
                LOGGER.info("RocketMQGoodsConsumer.onMessage(): Put away goods success -> {}", message);
            } else {
                // 获取下架商品的主键
                List<String> ids = JSONUtil.parseArray(content.get(MessageConstant.DATA)).toList(String.class);
                // 下架商品
                elasticSearchGoodsService.deleteBatch(ids);
                // 打印成功下架商品的日志
                LOGGER.info("RocketMQGoodsConsumer.onMessage(): Sold out goods success -> {}", message);
            }
        } catch (IOException e) {
            LOGGER.error("RocketMQGoodsConsumer.onMessage(): Failed to launch goods -> {}", e.getMessage());
        }
    }
}
