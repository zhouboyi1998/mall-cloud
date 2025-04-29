package com.cafe.review.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.review.model.entity.ReviewTag;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.review.service
 * @Author: zhouboyi
 * @Date: 2025-04-29
 * @Description: 评论-标签关联关系业务接口
 */
public interface ReviewTagService extends IService<ReviewTag> {

    /**
     * 删除旧的评论-标签关联关系, 保存新的评论-标签关联关系
     *
     * @param reviewId 评论ID
     * @param tagIds   新的标签ID列表
     * @return
     */
    void removeAndSave(Long reviewId, List<Long> tagIds);
}
