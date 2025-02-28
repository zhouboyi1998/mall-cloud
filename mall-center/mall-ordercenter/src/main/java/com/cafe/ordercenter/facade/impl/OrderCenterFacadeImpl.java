package com.cafe.ordercenter.facade.impl;

import com.cafe.common.constant.kafka.KafkaConstant;
import com.cafe.infrastructure.kafka.producer.KafkaProducer;
import com.cafe.order.model.vo.OrderVO;
import com.cafe.ordercenter.facade.OrderCenterFacade;
import com.cafe.ordercenter.service.OrderCenterService;
import com.cafe.storage.model.dto.CartDTO;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.transaction.Propagation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
