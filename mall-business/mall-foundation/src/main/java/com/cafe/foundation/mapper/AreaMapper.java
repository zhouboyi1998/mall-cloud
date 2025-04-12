package com.cafe.foundation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.foundation.model.entity.Area;
import com.cafe.foundation.model.vo.AreaDetailVO;
import com.cafe.foundation.model.vo.AreaTreeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * 根据省份id、城市id、区县id获取区域详情
     *
     * @param provinceId 省份ID
     * @param cityId     城市ID
     * @param districtId 区县ID
     * @return 区域详情
     */
    AreaDetailVO detail(
        @Param(value = "provinceId") Long provinceId,
        @Param(value = "cityId") Long cityId,
        @Param(value = "districtId") Long districtId
    );

    /**
     * 根据条件查询区域树形视图列表
     *
     * @param area 查询条件
     * @return 区域树形视图列表
     */
    List<AreaTreeVO> selectTreeVOList(@Param(value = "area") Area area);
}
