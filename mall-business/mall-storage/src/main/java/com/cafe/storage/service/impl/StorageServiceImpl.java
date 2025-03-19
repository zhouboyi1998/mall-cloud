package com.cafe.storage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.storage.mapper.StorageMapper;
import com.cafe.storage.model.entity.Storage;
import com.cafe.storage.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.storage.service.impl
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 仓库业务实现类
 */
@RequiredArgsConstructor
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements StorageService {

    private final StorageMapper storageMapper;
}
