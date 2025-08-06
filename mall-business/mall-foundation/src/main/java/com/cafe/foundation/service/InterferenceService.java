package com.cafe.foundation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.foundation.model.entity.Interference;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.service
 * @Author: zhouboyi
 * @Date: 2025-07-28
 * @Description: 干扰符业务接口
 */
public interface InterferenceService extends IService<Interference> {

    /**
     * 查询启用的干扰符列表
     *
     * @return 启用的干扰符列表
     */
    List<Interference> enableList();
}
