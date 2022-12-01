package com.cafe.id.service.impl;

import com.cafe.common.core.util.Snowflake;
import com.cafe.id.service.IDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.service.impl
 * @Author: zhouboyi
 * @Date: 2022/10/31 17:02
 * @Description:
 */
@Service(value = "SnowflakeIDServiceImpl")
public class SnowflakeIDServiceImpl implements IDService {

    private final Snowflake snowflake;

    @Autowired
    public SnowflakeIDServiceImpl(Snowflake snowflake) {
        this.snowflake = snowflake;
    }

    @Override
    public Long nextId() {
        return snowflake.nextId();
    }
}
