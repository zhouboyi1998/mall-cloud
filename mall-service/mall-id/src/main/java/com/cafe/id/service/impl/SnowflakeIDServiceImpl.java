package com.cafe.id.service.impl;

import com.cafe.common.lang.algorithm.id.Snowflake;
import com.cafe.id.service.IDService;
import com.cafe.id.support.IDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.service.impl
 * @Author: zhouboyi
 * @Date: 2022/10/31 17:02
 * @Description:
 */
@RequiredArgsConstructor
@Service
public class SnowflakeIDServiceImpl implements IDService {

    private final Snowflake snowflake;

    @Override
    public IDGenerator getKey() {
        return IDGenerator.SNOWFLAKE;
    }

    @Override
    public Long nextId() {
        return snowflake.nextId();
    }
}
