package com.cafe.id.service.impl;

import com.cafe.common.core.util.Snowflake;
import com.cafe.id.property.SnowflakeProperties;
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
    public SnowflakeIDServiceImpl(SnowflakeProperties snowflakeProperties) {
        snowflake = new Snowflake(snowflakeProperties.getWorkerId(), snowflakeProperties.getDatacenterId());
    }

    @Override
    public Long nextId() {
        return snowflake.nextId();
    }
}
