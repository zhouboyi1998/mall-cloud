package com.cafe.id.service.impl;

import com.cafe.common.lang.algorithm.id.Snowflake;
import com.cafe.id.property.IDProperties;
import com.cafe.id.service.IDService;
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
@Service(value = IDProperties.GeneratorServiceName.SNOWFLAKE)
public class SnowflakeIDServiceImpl implements IDService {

    private final Snowflake snowflake;

    @Override
    public Long nextId() {
        return snowflake.nextId();
    }
}
