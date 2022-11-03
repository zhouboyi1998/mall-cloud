package com.cafe.search.elasticsearch.message;

import cn.hutool.json.JSONUtil;
import com.cafe.common.constant.MonitorConstant;
import com.cafe.common.message.rocketmq.constant.RocketMQConsumerGroup;
import com.cafe.common.message.rocketmq.constant.RocketMQTopic;
import com.cafe.search.elasticsearch.constant.GoodsField;
import com.cafe.search.elasticsearch.model.Category;
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
 * @Date: 2022/7/29 22:10
 * @Description:
 */
@Component
@RocketMQMessageListener(consumerGroup = RocketMQConsumerGroup.GROUP_CATEGORY, topic = RocketMQTopic.CANAL_TO_ES_CATEGORY)
public class RocketMQCategoryConsumer implements RocketMQListener<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RocketMQCategoryConsumer.class);

    private ElasticSearchGoodsService elasticSearchGoodsService;

    @Autowired
    public RocketMQCategoryConsumer(ElasticSearchGoodsService elasticSearchGoodsService) {
        this.elasticSearchGoodsService = elasticSearchGoodsService;
    }

    @Override
    public void onMessage(String message) {
        // 打印成功接收消息的日志
        LOGGER.info("RocketMQCategoryConsumer.onMessage(): Category JSON Message -> {}", message);
        // 获取消息内容
        Map<String, Object> content = JSONUtil.parseObj(message);
        // 获取变更后的数据
        List<Category> afterDataList = JSONUtil.parseArray(content.get(MonitorConstant.AFTER_DATA)).toList(Category.class);

        // 更新所有改变的分类名称
        for (Category category : afterDataList) {
            try {
                // 批量更新商品
                elasticSearchGoodsService.updateBatch(GoodsField.CATEGORY_ID, category.getId(), GoodsField.CATEGORY_NAME, category.getCategoryName());
                // 打印成功更新分类名称的日志
                LOGGER.info("RocketMQCategoryConsumer.onMessage(): Update category name success -> {}", message);
            } catch (IOException e) {
                LOGGER.error("RocketMQCategoryConsumer.onMessage(): Failed to update category -> {}", e.getMessage());
            }
        }
    }
}
