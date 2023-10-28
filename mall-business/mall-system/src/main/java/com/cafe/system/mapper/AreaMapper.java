package com.cafe.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.system.bo.AreaDetail;
import com.cafe.system.model.Area;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.system.mapper
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 区域数据访问接口
 */
@Mapper
public interface AreaMapper extends BaseMapper<Area> {

    /**
     * 根据省份id、城市id、区县id获取详细信息
     *
     * @param provinceId
     * @param cityId
     * @param districtId
     * @return
     */
    AreaDetail detail(
        @Param(value = "provinceId") Long provinceId,
        @Param(value = "cityId") Long cityId,
        @Param(value = "districtId") Long districtId
    );
}
