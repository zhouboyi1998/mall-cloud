package com.cafe.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.manager.mapper.ThemeMapper;
import com.cafe.manager.model.Theme;
import com.cafe.manager.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.manager.service.impl
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
