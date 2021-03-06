package com.cafe.search.elasticsearch.message;

import cn.hutool.json.JSONUtil;
import com.cafe.common.constant.MonitorConstant;
import com.cafe.common.message.rocketmq.constant.RocketMQConsumerGroup;
import com.cafe.common.message.rocketmq.constant.RocketMQTopic;
import com.cafe.goods.constant.GoodsField;
import com.cafe.goods.model.Spu;
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
 * @Date: 2022/7/28 23:33
 * @Description:
 */
@Component
@RocketMQMessageListener(consumerGroup = RocketMQConsumerGroup.GROUP_ONE, topic = RocketMQTopic.CANAL_TO_ES_SPU)
public class RocketMQSpuConsumer implements RocketMQListener<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RocketMQSpuConsumer.class);

    private GoodsService goodsService;

    @Autowired
    public RocketMQSpuConsumer(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @Override
    public void onMessage(String message) {
        LOGGER.info("SPU JSON Message: {}", message);
        // 获取消息内容
        Map<String, Object> content = JSONUtil.parseObj(message);
        // 获取变更后的数据
        List<Spu> afterDataList
            = JSONUtil.parseArray(content.get(MonitorConstant.AFTER_DATA)).toList(Spu.class);

        // 更新所有改变的 SPU 名称
        for (Spu spu : afterDataList) {
            try {
                goodsService.updateBatchByQuery(
                    GoodsField.SPU_ID, spu.getId(),
                    GoodsField.SPU_NAME, spu.getSpuName()
                );
            } catch (IOException e) {
                LOGGER.error("Failed to update spu: {}", e.getMessage());
            }
        }
    }
}
