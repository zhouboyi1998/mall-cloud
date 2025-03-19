package com.cafe.common.constant.monitor;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.monitor
 * @Author: zhouboyi
 * @Date: 2022/5/19 17:22
 * @Description: 数据库监听相关常量
 */
public class MonitorConstant {

    /**
     * 更新操作
     */
    public static final String UPDATE = "update";

    /**
     * 新增操作
     */
    public static final String INSERT = "insert";

    /**
     * 删除操作
     */
    public static final String DELETE = "delete";

    /**
     * 监听到的操作类型
     */
    public static final String OPERATION = "operation";

    /**
     * 监听到的更新前数据
     */
    public static final String BEFORE_DATA = "before_data";

    /**
     * 监听到的更新后数据
     */
    public static final String AFTER_DATA = "after_data";

    /**
     * 监听到的数据来源
     */
    public static final String SOURCE = "source";
}
