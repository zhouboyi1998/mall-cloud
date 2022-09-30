package com.cafe.search.elasticsearch.message;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.cafe.common.constant.MonitorConstant;
import com.cafe.common.message.rocketmq.constant.RocketMQConsumerGroup;
import com.cafe.common.message.rocketmq.constant.RocketMQTopic;
import com.cafe.goods.model.Sku;
import com.cafe.search.elasticsearch.service.GoodsService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.elasticsearch.message
 * @Author: zhouboyi
 * @Date: 2022/7/29 22:29
 * @Description:
 */
@Component
@RocketMQMessageListener(consumerGroup = RocketMQConsumerGroup.GROUP_SKU, topic = RocketMQTopic.CANAL_TO_ES_SKU)
public class RocketMQSkuConsumer implements RocketMQListener<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RocketMQSkuConsumer.class);

    private GoodsService goodsService;

    @Autowired
    public RocketMQSkuConsumer(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @Override
    public void onMessage(String message) {
        LOGGER.info("RocketMQSkuConsumer.onMessage(): SKU JSON Message -> {}", message);
        // 获取消息内容
        Map<String, Object> content = JSONUtil.parseObj(message);
        // 获取变更的类型
        String operation = content.get(MonitorConstant.OPERATION).toString();
        // 获取变更前的数据
        List<Sku> beforeDataList
            = JSONUtil.parseArray(content.get(MonitorConstant.BEFORE_DATA)).toList(Sku.class);
        // 获取变更后的数据
        List<Sku> afterDataList
            = JSONUtil.parseArray(content.get(MonitorConstant.AFTER_DATA)).toList(Sku.class);

        // 根据变更的类型执行不同的更新 ElasticSearch 操作
        try {
            if (ObjectUtil.equal(MonitorConstant.UPDATE, operation) || ObjectUtil.equal(MonitorConstant.DELETE, operation)) {
                List<String> removeIds = new ArrayList<String>();
                for (Sku sku : beforeDataList) {
                    removeIds.add(sku.getId().toString());
                }
                goodsService.deleteBatch(removeIds);
            }
            if (ObjectUtil.equal(MonitorConstant.UPDATE, operation) || ObjectUtil.equal(MonitorConstant.INSERT, operation)) {
                List<Long> importIds = new ArrayList<Long>();
                for (Sku sku : afterDataList) {
                    importIds.add(sku.getId());
                }
                goodsService.importBatch(importIds);
            }
        } catch (IOException e) {
            LOGGER.error("RocketMQSkuConsumer.onMessage(): Failed to update sku -> {}", e.getMessage());
        }
    }
}
