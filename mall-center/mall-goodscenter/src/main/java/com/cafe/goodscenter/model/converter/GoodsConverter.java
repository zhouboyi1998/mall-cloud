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
 * @Description:
 */
@Mapper(componentModel = "spring")
public interface GoodsConverter {

    GoodsConverter INSTANCE = Mappers.getMapper(GoodsConverter.class);

    /**
     * 商品业务模型 -> 商品概要模型
     *
     * @param goods
     * @return
     */
    GoodsSummary toSummary(Goods goods);

    /**
     * 商品业务模型列表 -> 商品概要模型列表
     *
     * @param goodsList
     * @return
     */
    List<GoodsSummary> toSummaryList(List<Goods> goodsList);

    /**
     * SPU 视图模型 -> SPU 详情模型
     *
     * @param spuVO
     * @return
     */
    SpuDetail toDetail(SpuVO spuVO);
}
