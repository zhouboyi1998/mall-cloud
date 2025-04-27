package com.cafe.common.util.builder;

import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.builder
 * @Author: zhouboyi
 * @Date: 2025/4/15 17:36
 * @Description: ToString 样式持有者
 */
public class ToStringStyleHolder {

    public static final ToStringStyle JSON_STYLE_WITHOUT_UNICODE = new JsonToStringStyleWithoutUnicode();
}
