package com.cafe.foundation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.foundation.model.entity.Sensitive;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.mapper
 * @Author: zhouboyi
 * @Date: 2025-07-27
 * @Description: 敏感词数据访问接口
 */
@Mapper
public interface SensitiveMapper extends BaseMapper<Sensitive> {

}
