package com.cafe.foundation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.common.lang.tree.Tree;
import com.cafe.common.mybatisplus.util.WrapperUtil;
import com.cafe.common.util.tree.TreeUtil;
import com.cafe.foundation.converter.AreaConverter;
import com.cafe.foundation.dto.AreaDTO;
import com.cafe.foundation.mapper.AreaMapper;
import com.cafe.foundation.model.Area;
import com.cafe.foundation.service.AreaService;
import com.cafe.foundation.vo.AreaTreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Tree> treeList() {
        List<Area> areaList = areaMapper.selectList(WrapperUtil.emptyQueryWrapper());
        List<AreaTreeVO> treeVOList = AreaConverter.INSTANCE.toTreeVOList(areaList);
        return TreeUtil.buildTreeList(treeVOList);
    }
}
