package com.cafe.storage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.storage.mapper.StockMapper;
import com.cafe.storage.model.Stock;
import com.cafe.storage.service.StockService;
import com.cafe.storage.vo.CartVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    @Override
    public void inboundBatch(List<CartVO> cartVOList) {
        cartVOList.forEach(stockMapper::inbound);
    }

    @Override
    public List<String> outboundBatch(List<CartVO> cartVOList) {
        // 遍历购物车中的 SKU, 逐个出库, 收集库存不足的 SKU 主键
        List<Long> failIds = cartVOList.stream()
            .map(cartVO -> stockMapper.outbound(cartVO) < 1 ? cartVO.getSkuId() : null)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        // 如果有 SKU 出库失败, 回滚出库成功的 SKU 的库存
        if (failIds.size() > 0) {
            cartVOList.stream()
                .filter(cartVO -> !failIds.contains(cartVO.getSkuId()))
                .forEach(stockMapper::inbound);
        }

        // 返回库存不足的 SKU 主键列表
        return failIds.stream().map(String::valueOf).collect(Collectors.toList());
    }
}
