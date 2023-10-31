package com.cafe.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.system.dto.AreaDTO;
import com.cafe.system.model.Area;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.system.service
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 区域业务接口
 */
public interface AreaService extends IService<Area> {

    /**
     * 根据省份id、城市id、区县id获取区域
     *
     * @param provinceId
     * @param cityId
     * @param districtId
     * @return
     */
    AreaDTO dto(Long provinceId, Long cityId, Long districtId);
}
