package com.cafe.id.service.impl;

import com.cafe.id.service.IDService;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.service.impl
 * @Author: zhouboyi
 * @Date: 2022/10/31 17:29
 * @Description:
 */
@Service(value = "ZeroIDServiceImpl")
public class ZeroIDServiceImpl implements IDService {

    @Override
    public Long nextId() {
        return 0L;
    }
}
