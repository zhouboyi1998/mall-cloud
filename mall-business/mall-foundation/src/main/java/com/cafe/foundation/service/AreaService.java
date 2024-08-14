package com.cafe.foundation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.common.lang.tree.Tree;
import com.cafe.foundation.model.Area;
import com.cafe.foundation.vo.AreaDetailVO;

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
     * 根据省份id、城市id、区县id获取区域详情
     *
     * @param provinceId
     * @param cityId
     * @param districtId
     * @return
     */
    AreaDetailVO detail(Long provinceId, Long cityId, Long districtId);

    /**
     * 根据条件查询区域树列表
     *
     * @param area
     * @return
     */
    List<Tree> treeList(Area area);
}
