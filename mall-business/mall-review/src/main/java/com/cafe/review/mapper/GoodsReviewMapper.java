package com.cafe.review.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.review.model.entity.GoodsReview;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.review.mapper
 * @Author: zhouboyi
 * @Date: 2025-04-29
 * @Description: 商品-评论关联关系数据访问接口
 */
@Mapper
public interface GoodsReviewMapper extends BaseMapper<GoodsReview> {

}
