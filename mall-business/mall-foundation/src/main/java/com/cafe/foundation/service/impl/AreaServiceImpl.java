package com.cafe.foundation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.common.core.exception.BusinessException;
import com.cafe.common.enumeration.http.HttpStatusEnum;
import com.cafe.common.lang.tree.Tree;
import com.cafe.common.util.tree.TreeUtil;
import com.cafe.foundation.mapper.AreaMapper;
import com.cafe.foundation.model.Area;
import com.cafe.foundation.service.AreaService;
import com.cafe.foundation.vo.AreaDetailVO;
import com.cafe.foundation.vo.AreaTreeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public List<Tree> treeList(Area area) {
        if (Objects.isNull(area.getParentId())) {
            throw new BusinessException(HttpStatusEnum.FAIL.value(), "上级区域id不允许为空", area);
        }
        List<AreaTreeVO> treeList = areaMapper.selectTreeVOList(area);
        return TreeUtil.buildTreeList(treeList, area.getParentId());
    }
}
