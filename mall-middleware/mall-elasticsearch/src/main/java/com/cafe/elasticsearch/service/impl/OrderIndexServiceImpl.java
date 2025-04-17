package com.cafe.elasticsearch.service.impl;

import com.cafe.elasticsearch.model.index.OrderIndex;
import com.cafe.elasticsearch.repository.OrderIndexRepository;
import com.cafe.elasticsearch.service.OrderIndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.service.impl
 * @Author: zhouboyi
 * @Date: 2024/6/28 16:18
 * @Description:
 */
@RequiredArgsConstructor
@Service
public class OrderIndexServiceImpl implements OrderIndexService {

    private final OrderIndexRepository orderIndexRepository;

    @Override
    public OrderIndex one(Long id) {
        return orderIndexRepository.findById(id).orElse(new OrderIndex());
    }

    @Override
    public OrderIndex save(OrderIndex orderIndex) {
        return orderIndexRepository.save(orderIndex);
    }

    @Override
    public List<OrderIndex> saveBatch(List<OrderIndex> orderIndexList) {
        Iterable<OrderIndex> saveIterable = orderIndexRepository.saveAll(orderIndexList);
        return StreamSupport.stream(saveIterable.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public OrderIndex update(OrderIndex orderIndex) {
        return orderIndexRepository.update(orderIndex);
    }

    @Override
    public List<OrderIndex> updateBatch(List<OrderIndex> orderIndexList) {
        Iterable<OrderIndex> updateIterable = orderIndexRepository.updateAll(orderIndexList);
        return StreamSupport.stream(updateIterable.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        orderIndexRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        List<OrderIndex> orderIndexList = ids.stream().map(id -> new OrderIndex().setId(id)).collect(Collectors.toList());
        orderIndexRepository.deleteAll(orderIndexList);
    }
}
