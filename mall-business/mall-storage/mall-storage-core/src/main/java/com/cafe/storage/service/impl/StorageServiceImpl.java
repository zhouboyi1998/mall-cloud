package com.cafe.storage.service.impl;

import com.cafe.storage.model.Storage;
import com.cafe.storage.mapper.StorageMapper;
import com.cafe.storage.service.StorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.storage.service.impl
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 仓库业务实现类
 */
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements StorageService {

    private final StorageMapper storageMapper;

    @Autowired
    public StorageServiceImpl(StorageMapper storageMapper) {
        this.storageMapper = storageMapper;
    }
}
