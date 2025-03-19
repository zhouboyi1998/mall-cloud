package com.cafe.solr.model.converter;

import com.cafe.solr.model.dto.GoodsIndexDTO;
import com.cafe.solr.model.index.GoodsIndex;
import org.mapstruct.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.solr.model.converter
 * @Author: zhouboyi
 * @Date: 2024/12/25 18:15
 * @Description: 商品全文索引模型转换器
 */
@Mapper(componentModel = "spring")
public interface GoodsIndexConverter {

    /**
     * 数据传输模型 -> 实体模型
     *
     * @param goodsIndexDTO 数据传输模型
     * @return 实体模型
     */
    GoodsIndex toEntity(GoodsIndexDTO goodsIndexDTO);

    /**
     * 实体模型 -> 数据传输模型
     *
     * @param goodsIndex 实体模型
     * @return 数据传输模型
     */
    GoodsIndexDTO toDTO(GoodsIndex goodsIndex);
}
