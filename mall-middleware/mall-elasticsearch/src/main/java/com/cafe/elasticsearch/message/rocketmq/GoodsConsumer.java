package com.cafe.elasticsearch.message.rocketmq;

import com.cafe.common.constant.app.FieldConstant;
import com.cafe.common.constant.model.GoodsConstant;
import com.cafe.common.constant.rocketmq.RocketMQConstant;
import com.cafe.common.json.util.JacksonUtil;
import com.cafe.common.util.builder.ToStringStyleHolder;
import com.cafe.elasticsearch.model.index.GoodsIndex;
import com.cafe.elasticsearch.service.GoodsIndexService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.message.rocketmq
 * @Author: zhouboyi
 * @Date: 2022/11/3 15:24
 * @Description: RocketMQ 商品消息消费者
 */
@Slf4j
@RequiredArgsConstructor
@Component
@RocketMQMessageListener(
    topic = RocketMQConstant.Topic.GOODS_INDEX,
    consumerGroup = RocketMQConstant.ConsumerGroup.ELASTICSEARCH,
    // 消费模式: 顺序
    consumeMode = ConsumeMode.ORDERLY,
    // 订阅模式: 集群
    messageModel = MessageModel.CLUSTERING
)
public class GoodsConsumer implements RocketMQListener<String> {

    private final GoodsIndexService goodsIndexService;

    @Override
    public void onMessage(String message) {
        // 打印成功接收消息的日志
        log.info("GoodsConsumer.onMessage(): rocketmq message -> {}", message);
        // 获取消息内容
        Map<String, Object> content = JacksonUtil.readValue(message, new TypeReference<Map<String, Object>>() {});
        // 获取上下架标识
        Integer status = Integer.parseInt(String.valueOf(content.get(FieldConstant.STATUS)));
        // 处理上下架请求
        if (GoodsConstant.Status.ON_SHELVE.equals(status)) {
            // 获取上架商品的信息
            GoodsIndex goodsIndex = JacksonUtil.convertValue(content.get(FieldConstant.DATA), GoodsIndex.class);
            // 上架商品
            GoodsIndex save = goodsIndexService.save(goodsIndex);
            // 打印日志
            log.info("GoodsConsumer.onMessage(): Goods on shelve success! goods index -> {}", ToStringBuilder.reflectionToString(save, ToStringStyleHolder.JSON_STYLE_WITHOUT_UNICODE));
        } else if (GoodsConstant.Status.OFF_SHELVE.equals(status)) {
            // 获取下架商品的主键
            Long id = JacksonUtil.convertValue(content.get(FieldConstant.DATA), Long.class);
            // 下架商品
            goodsIndexService.delete(id);
            // 打印日志
            log.info("GoodsConsumer.onMessage(): Goods off shelve success! goods index id -> {}", id);
        } else {
            log.error("GoodsConsumer.onMessage(): status [{}] is invalid!", status);
        }
    }
}
