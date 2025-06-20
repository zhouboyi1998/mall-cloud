package com.cafe.component.grinder.core.filter.support;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.core.result
 * @Author: zhouboyi
 * @Date: 2025/6/23 11:57
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum GrinderFilterStatus {

    /**
     * 成功
     */
    SUCCESS,

    /**
     * 失败
     */
    FAILED,

    /**
     * 跳过
     */
    SKIPPED,

    /**
     * 禁用
     */
    DISABLED,
}
