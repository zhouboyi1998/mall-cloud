package com.cafe.storage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.storage.mapper.StockMapper;
import com.cafe.storage.model.dto.CartDTO;
import com.cafe.storage.model.entity.Stock;
import com.cafe.storage.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.storage.service.impl
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 库存业务实现类
 */
@RequiredArgsConstructor
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {

    private final StockMapper stockMapper;

    @Transactional(
        propagation = Propagation.REQUIRED,
        rollbackFor = Exception.class,
        timeout = 10,
        isolation = Isolation.REPEATABLE_READ
    )
    @Override
    public List<Long> inboundBatch(List<CartDTO> cartDTOList) {
        // 遍历购物车 DTO 中的 SKU, 逐个入库, 收集入库失败的 SKU 主键
        return cartDTOList.stream()
            .map(cartDTO -> stockMapper.inbound(cartDTO) < 1 ? cartDTO.getSkuId() : null)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    @Transactional(
        propagation = Propagation.REQUIRED,
        rollbackFor = Exception.class,
        timeout = 10,
        isolation = Isolation.REPEATABLE_READ
    )
    @Override
    public List<Long> outboundBatch(List<CartDTO> cartDTOList) {
        // 遍历购物车 DTO 中的 SKU, 逐个出库, 收集库存不足的 SKU 主键
        List<Long> outboundFailIds = cartDTOList.stream()
            .map(cartDTO -> stockMapper.outbound(cartDTO) < 1 ? cartDTO.getSkuId() : null)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        // 如果有 SKU 出库失败, 回滚出库成功的 SKU 的库存
        if (!CollectionUtils.isEmpty(outboundFailIds)) {
            cartDTOList.stream()
                .filter(cartDTO -> !outboundFailIds.contains(cartDTO.getSkuId()))
                .forEach(stockMapper::inbound);
        }

        // 返回库存不足的 SKU 主键列表
        return outboundFailIds;
    }
}
