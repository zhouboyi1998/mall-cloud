package com.cafe.foundation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.common.lang.tree.Tree;
import com.cafe.foundation.dto.AreaDTO;
import com.cafe.foundation.model.Area;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.service
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

    /**
     * 查询区域树
     *
     * @return
     */
    List<Tree> treeList();
}
