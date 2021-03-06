package com.cafe.search.elasticsearch.message;

import cn.hutool.json.JSONUtil;
import com.cafe.common.constant.MonitorConstant;
import com.cafe.common.message.rocketmq.constant.RocketMQConsumerGroup;
import com.cafe.common.message.rocketmq.constant.RocketMQTopic;
import com.cafe.goods.constant.GoodsField;
import com.cafe.goods.model.Brand;
import com.cafe.search.elasticsearch.service.GoodsService;
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
 * @Date: 2022/7/29 22:06
 * @Description:
 */
@Component
@RocketMQMessageListener(consumerGroup = RocketMQConsumerGroup.GROUP_ONE, topic = RocketMQTopic.CANAL_TO_ES_BRAND)
public class RocketMQBrandConsumer implements RocketMQListener<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RocketMQBrandConsumer.class);

    private GoodsService goodsService;

    @Autowired
    public RocketMQBrandConsumer(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @Override
    public void onMessage(String message) {
        LOGGER.info("Brand JSON Message: {}", message);
        // 获取消息内容
        Map<String, Object> content = JSONUtil.parseObj(message);
        // 获取变更后的数据
        List<Brand> afterDataList
            = JSONUtil.parseArray(content.get(MonitorConstant.AFTER_DATA)).toList(Brand.class);

        // 更新所有改变的品牌名称
        for (Brand brand : afterDataList) {
            try {
                goodsService.updateBatchByQuery(
                    GoodsField.BRAND_ID, brand.getId(),
                    GoodsField.BRAND_NAME, brand.getBrandName()
                );
            } catch (IOException e) {
                LOGGER.error("Failed to update brand: {}", e.getMessage());
            }
        }
    }
}
