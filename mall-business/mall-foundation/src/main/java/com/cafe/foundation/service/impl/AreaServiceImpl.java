package com.cafe.foundation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.common.util.tree.TreeUtil;
import com.cafe.foundation.mapper.AreaMapper;
import com.cafe.foundation.model.converter.AreaConverter;
import com.cafe.foundation.model.entity.Area;
import com.cafe.foundation.model.query.AreaTreeListQuery;
import com.cafe.foundation.model.vo.AreaDetailVO;
import com.cafe.foundation.model.vo.AreaTreeVO;
import com.cafe.foundation.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.service.impl
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 区域业务实现类
 */
@RequiredArgsConstructor
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {

    private final AreaMapper areaMapper;

    @Override
    public AreaDetailVO detail(Long provinceId, Long cityId, Long districtId) {
        return areaMapper.detail(provinceId, cityId, districtId);
    }

    @Override
    public List<AreaTreeVO> treeList(AreaTreeListQuery query) {
        Area area = AreaConverter.INSTANCE.toEntity(query);
        List<AreaTreeVO> areaTreeVOList = areaMapper.selectTreeVOList(area);
        return TreeUtil.buildTreeList(areaTreeVOList, query.getParentId());
    }
}
