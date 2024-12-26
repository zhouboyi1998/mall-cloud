package com.cafe.goodscenter.model.converter;

import com.cafe.goods.model.bo.Goods;
import com.cafe.goods.model.vo.SpuVO;
import com.cafe.goodscenter.model.vo.GoodsSummary;
import com.cafe.goodscenter.model.vo.SpuDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goodscenter.model.converter
 * @Author: zhouboyi
 * @Date: 2024/8/4 21:39
 * @Description: 商品模型转换器
 */
@Mapper(componentModel = "spring")
public interface GoodsConverter {

    GoodsConverter INSTANCE = Mappers.getMapper(GoodsConverter.class);

    /**
     * 商品业务模型 -> 商品概要模型
     *
     * @param goods 商品业务模型
     * @return 商品概要模型
     */
    GoodsSummary toSummary(Goods goods);

    /**
     * 商品业务模型列表 -> 商品概要模型列表
     *
     * @param goodsList 商品业务模型列表
     * @return 商品概要模型列表
     */
    List<GoodsSummary> toSummaryList(List<Goods> goodsList);

    /**
     * SPU 视图模型 -> SPU 详情模型
     *
     * @param spuVO SPU 视图模型
     * @return SPU 详情模型
     */
    SpuDetail toDetail(SpuVO spuVO);
}
