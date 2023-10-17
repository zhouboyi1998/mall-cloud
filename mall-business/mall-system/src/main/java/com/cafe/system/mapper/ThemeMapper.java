package com.cafe.system.mapper;

import com.cafe.system.model.Theme;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.system.mapper
 * @Author: zhouboyi
 * @Date: 2023-05-16
 * @Description: 主题数据访问接口
 */
@Mapper
public interface ThemeMapper extends BaseMapper<Theme> {

}
