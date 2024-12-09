package com.cafe.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.manager.model.entity.Theme;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.manager.mapper
 * @Author: zhouboyi
 * @Date: 2023-05-16
 * @Description: 主题数据访问接口
 */
@Mapper
public interface ThemeMapper extends BaseMapper<Theme> {

}
