package com.cafe.id.service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.service
 * @Author: zhouboyi
 * @Date: 2022/10/31 17:01
 * @Description:
 */
public interface IDService {

    /**
     * 获取下一个分布式ID
     *
     * @return 分布式ID
     */
    Long nextId();
}
