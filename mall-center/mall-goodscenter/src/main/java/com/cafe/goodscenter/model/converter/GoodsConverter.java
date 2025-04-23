package com.cafe.goodscenter.model.converter;

import com.cafe.elasticsearch.model.index.GoodsIndex;
import com.cafe.goods.model.bo.Goods;
import com.cafe.goods.model.vo.SpuVO;
import com.cafe.goodscenter.model.vo.GoodsDetail;
import com.cafe.goodscenter.model.vo.GoodsSummary;
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
     * 商品业务模型 -> 商品全文索引模型
     *
     * @param goods 商品业务模型
     * @return 商品全文索引模型
     */
    GoodsIndex toIndex(Goods goods);

    /**
     * 商品业务模型列表 -> 商品全文索引模型列表
     *
     * @param goodsList 商品业务模型列表
     * @return 商品全文索引模型列表
     */
    List<GoodsIndex> toIndexList(List<Goods> goodsList);

    /**
     * 商品业务模型 -> 商品概要视图模型
     *
     * @param goods 商品业务模型
     * @return 商品概要视图模型
     */
    GoodsSummary toSummary(Goods goods);

    /**
     * 商品业务模型列表 -> 商品概要视图模型列表
     *
     * @param goodsList 商品业务模型列表
     * @return 商品概要视图模型列表
     */
    List<GoodsSummary> toSummaryList(List<Goods> goodsList);

    /**
     * SPU 视图模型 -> 商品详情视图模型
     *
     * @param spuVO SPU 视图模型
     * @return 商品详情视图模型
     */
    GoodsDetail toDetail(SpuVO spuVO);
}
