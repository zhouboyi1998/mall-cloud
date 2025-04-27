package com.cafe.infrastructure.elasticsearch.model.converter;

import com.cafe.infrastructure.elasticsearch.model.vo.AggregatedPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.elasticsearch.model.converter
 * @Author: zhouboyi
 * @Date: 2025/4/23 23:08
 * @Description: 分页模型转换器
 */
@Mapper(componentModel = "spring")
public interface PageConverter {

    PageConverter INSTANCE = Mappers.getMapper(PageConverter.class);

    /**
     * 聚合分页模型 -> 聚合分页视图模型
     *
     * @param aggregatedPage 聚合分页模型
     * @param <T>            分页内容类型
     * @return 聚合分页视图模型
     */
    default <T> AggregatedPageVO<T> toAggregatedPageVO(AggregatedPageImpl<T> aggregatedPage) {
        return new AggregatedPageVO<T>()
            .setContent(aggregatedPage.getContent())
            .setScrollId(aggregatedPage.getScrollId())
            .setMaxScore(aggregatedPage.getMaxScore())
            .setTotalElements(aggregatedPage.getTotalElements())
            .setTotalPages(aggregatedPage.getTotalPages())
            .setSize(aggregatedPage.getSize())
            .setNumber(aggregatedPage.getNumber())
            .setNumberOfElements(aggregatedPage.getNumberOfElements())
            .setFirst(aggregatedPage.isFirst())
            .setLast(aggregatedPage.isLast())
            .setEmpty(aggregatedPage.isEmpty());
    }

    /**
     * 源聚合分页视图模型 -> 目标聚合分页视图模型
     *
     * @param source 源聚合分页视图模型
     * @param target 目标聚合分页视图模型
     * @param <S>    源分页内容类型
     * @param <T>    目标分页内容类型
     * @return 目标聚合分页视图模型
     */
    default <S, T> AggregatedPageVO<T> convertAggregatedPageVOType(AggregatedPageVO<S> source, AggregatedPageVO<T> target) {
        return target
            .setScrollId(source.getScrollId())
            .setMaxScore(source.getMaxScore())
            .setTotalElements(source.getTotalElements())
            .setTotalPages(source.getTotalPages())
            .setSize(source.getSize())
            .setNumber(source.getNumber())
            .setNumberOfElements(source.getNumberOfElements())
            .setFirst(source.getFirst())
            .setLast(source.getLast())
            .setEmpty(source.getEmpty());
    }
}
