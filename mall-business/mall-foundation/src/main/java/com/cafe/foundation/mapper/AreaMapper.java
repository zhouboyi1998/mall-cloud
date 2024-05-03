package com.cafe.foundation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.foundation.dto.AreaDTO;
import com.cafe.foundation.model.Area;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.mapper
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 区域数据访问接口
 */
@Mapper
public interface AreaMapper extends BaseMapper<Area> {

    /**
     * 根据省份id、城市id、区县id获取区域
     *
     * @param provinceId
     * @param cityId
     * @param districtId
     * @return
     */
    AreaDTO dto(
        @Param(value = "provinceId") Long provinceId,
        @Param(value = "cityId") Long cityId,
        @Param(value = "districtId") Long districtId
    );
}
