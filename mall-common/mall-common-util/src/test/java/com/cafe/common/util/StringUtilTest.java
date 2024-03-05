package com.cafe.common.util;

import org.junit.jupiter.api.Test;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util
 * @Author: zhouboyi
 * @Date: 2024/3/5 15:12
 * @Description:
 */
class StringUtilTest {

    @Test
    void upperCaseFirstLetter() {
        String upperCaseFirstLetter = StringUtil.upperCaseFirstLetter("cloud");
        System.out.println(upperCaseFirstLetter);
    }

    @Test
    void lowerCaseFirstLetter() {
        String lowerCaseFirstLetter = StringUtil.lowerCaseFirstLetter("CLOUD");
        System.out.println(lowerCaseFirstLetter);
    }
}
