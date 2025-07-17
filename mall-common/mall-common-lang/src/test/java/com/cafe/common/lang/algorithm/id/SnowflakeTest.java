package com.cafe.common.lang.algorithm.id;

import org.junit.jupiter.api.Test;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.lang.algorithm.id
 * @Author: zhouboyi
 * @Date: 2023/7/28 16:15
 * @Description:
 */
class SnowflakeTest {

    @Test
    void nextId() {
        Snowflake snowflake = new Snowflake(1, 1);
        for (int i = 0; i < 10; i++) {
            System.out.println(snowflake.nextId());
        }
    }
}
