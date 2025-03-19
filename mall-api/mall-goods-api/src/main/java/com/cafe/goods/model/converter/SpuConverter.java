package com.cafe.goods.model.converter;

import com.cafe.goods.model.entity.Spu;
import com.cafe.goods.model.vo.SpuVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.model.converter
 * @Author: zhouboyi
 * @Date: 2024/8/4 23:24
 * @Description: 标准化产品单元模型转换器
 */
@Mapper(componentModel = "spring")
public interface SpuConverter {

    SpuConverter INSTANCE = Mappers.getMapper(SpuConverter.class);

    /**
     * 实体模型 -> 视图模型
     *
     * @param spu 实体模型
     * @return 视图模型
     */
    SpuVO toVO(Spu spu);
}
