package com.cafe.review.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.review.model.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.review.mapper
 * @Author: zhouboyi
 * @Date: 2025-04-29
 * @Description: 标签数据访问接口
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}
