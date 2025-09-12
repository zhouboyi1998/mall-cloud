package com.cafe.id.service;

import com.cafe.common.lang.pattern.strategy.IStrategy;
import com.cafe.id.support.IDGenerator;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.service
 * @Author: zhouboyi
 * @Date: 2022/10/31 17:01
 * @Description:
 */
public interface IDService extends IStrategy<IDGenerator> {

    /**
     * 获取下一个分布式ID
     *
     * @return 分布式ID
     */
    Long nextId();
}
