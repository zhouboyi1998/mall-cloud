package com.cafe.foundation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.common.lang.tree.Tree;
import com.cafe.foundation.model.entity.Area;
import com.cafe.foundation.model.query.AreaTreeListQuery;
import com.cafe.foundation.model.vo.AreaDetailVO;

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
     * @param provinceId 省份ID
     * @param cityId     城市ID
     * @param districtId 区县ID
     * @return 区域详情
     */
    AreaDetailVO detail(Long provinceId, Long cityId, Long districtId);

    /**
     * 根据条件查询区域树列表
     *
     * @param query 查询条件
     * @return 区域树列表
     */
    List<Tree> treeList(AreaTreeListQuery query);
}
