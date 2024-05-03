package com.cafe.foundation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.foundation.dto.AreaDTO;
import com.cafe.foundation.mapper.AreaMapper;
import com.cafe.foundation.model.Area;
import com.cafe.foundation.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.service.impl
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 区域业务实现类
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {

    private final AreaMapper areaMapper;

    @Autowired
    public AreaServiceImpl(AreaMapper areaMapper) {
        this.areaMapper = areaMapper;
    }

    @Override
    public AreaDTO dto(Long provinceId, Long cityId, Long districtId) {
        return areaMapper.dto(provinceId, cityId, districtId);
    }
}
