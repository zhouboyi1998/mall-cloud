package com.cafe.system.service.impl;

import com.cafe.system.model.Theme;
import com.cafe.system.mapper.ThemeMapper;
import com.cafe.system.service.ThemeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.system.service.impl
 * @Author: zhouboyi
 * @Date: 2023-05-16
 * @Description: 主题业务实现类
 */
@Service
public class ThemeServiceImpl extends ServiceImpl<ThemeMapper, Theme> implements ThemeService {

    private final ThemeMapper themeMapper;

    @Autowired
    public ThemeServiceImpl(ThemeMapper themeMapper) {
        this.themeMapper = themeMapper;
    }
}
