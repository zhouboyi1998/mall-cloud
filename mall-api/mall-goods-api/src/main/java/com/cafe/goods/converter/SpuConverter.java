package com.cafe.goods.converter;

import com.cafe.goods.model.Spu;
import com.cafe.goods.vo.SpuVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.converter
 * @Author: zhouboyi
 * @Date: 2024/8/4 23:24
 * @Description:
 */
@Mapper(componentModel = "spring")
public interface SpuConverter {

    SpuConverter INSTANCE = Mappers.getMapper(SpuConverter.class);

    /**
     * 实体模型 -> 视图模型
     *
     * @param spu
     * @return
     */
    SpuVO toVO(Spu spu);
}
