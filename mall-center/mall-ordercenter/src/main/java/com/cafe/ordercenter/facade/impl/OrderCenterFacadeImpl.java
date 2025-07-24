package com.cafe.ordercenter.facade.impl;

import com.cafe.common.constant.kafka.KafkaConstant;
import com.cafe.common.constant.model.OrderConstant;
import com.cafe.infrastructure.kafka.producer.KafkaProducer;
import com.cafe.infrastructure.redis.annotation.Idempotence;
import com.cafe.order.model.vo.OrderVO;
import com.cafe.ordercenter.facade.OrderCenterFacade;
import com.cafe.ordercenter.model.message.GoodsReviewMessage;
import com.cafe.ordercenter.service.OrderCenterService;
import com.cafe.review.model.query.OrderReviewAndGoodsReviewSaveQuery;
import com.cafe.storage.model.dto.CartDTO;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.transaction.Propagation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.ordercenter.facade.impl
 * @Author: zhouboyi
 * @Date: 2025/3/24 23:06
 * @Description: 订单中心防腐层实现类
 */
@RequiredArgsConstructor
@Service
public class OrderCenterFacadeImpl implements OrderCenterFacade {

    private final OrderCenterService orderCenterService;

    private final KafkaProducer kafkaProducer;

    @Idempotence(value = 300)
    @GlobalTransactional(
        propagation = Propagation.REQUIRED,
        rollbackFor = Exception.class,
        timeoutMills = 180000,
        lockRetryInterval = 10
    )
    @Override
    public OrderVO submit(Long addressId, List<CartDTO> cartDTOList) {
        // 提交订单
        OrderVO orderVO = orderCenterService.submit(addressId, cartDTOList);
        // 发送订单信息到 Kafka
        kafkaProducer.send(KafkaConstant.Topic.ORDER_INDEX, orderVO);
        return orderVO;
    }

    @GlobalTransactional(
        propagation = Propagation.REQUIRED,
        rollbackFor = Exception.class,
        timeoutMills = 120000,
        lockRetryInterval = 10
    )
    @Override
    public List<Long> cancel(LocalDateTime now, Integer duration) {
        // 取消超时未支付的订单
        List<Long> cancelOrderIds = orderCenterService.cancel(now, duration);
        // 发送订单信息到 Kafka
        cancelOrderIds.stream()
            .map(cancelOrderId -> new OrderVO().setId(cancelOrderId).setStatus(OrderConstant.Status.CANCEL))
            .forEach(orderVO -> kafkaProducer.send(KafkaConstant.Topic.ORDER_INDEX, orderVO));
        return cancelOrderIds;
    }

    @GlobalTransactional(
        propagation = Propagation.REQUIRED,
        rollbackFor = Exception.class,
        timeoutMills = 45000,
        lockRetryInterval = 10
    )
    @Override
    public void review(OrderReviewAndGoodsReviewSaveQuery query) {
        // 评价订单
        orderCenterService.review(query);
        // 发送商品评价信息到 Kafka
        query.getGoodsReviewSaveQueryList().stream()
            // 封装商品评价消息体必要信息, 减小消息体体积
            .map(goodsReviewSaveQuery -> new GoodsReviewMessage()
                .setSkuId(goodsReviewSaveQuery.getSkuId())
                .setRating(goodsReviewSaveQuery.getReview().getRating()))
            .forEach(goodsReviewMessage -> kafkaProducer.send(KafkaConstant.Topic.GOODS_REVIEW, goodsReviewMessage));
    }
}
