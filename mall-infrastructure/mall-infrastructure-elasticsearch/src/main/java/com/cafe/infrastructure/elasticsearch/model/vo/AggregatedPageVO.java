package com.cafe.infrastructure.elasticsearch.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.elasticsearch.model.vo
 * @Author: zhouboyi
 * @Date: 2025/4/23 22:54
 * @Description: 聚合分页视图模型
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AggregatedPageVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分页内容
     */
    private List<T> content;

    /**
     * 滚动查询ID
     */
    private String scrollId;

    /**
     * 最大得分 (匹配度最高的文档的得分)
     */
    private Float maxScore;

    /**
     * 符合条件的文档总数
     */
    private Long totalElements;

    /**
     * 总页数
     */
    private Integer totalPages;

    /**
     * 每页显示的文档数量
     */
    private Integer size;

    /**
     * 当前页的索引 (类似页码, 从 0 开始计数)
     */
    private Integer number;

    /**
     * 当前页实际显示的文档数量 (最后一页可能小于 size)
     */
    private Integer numberOfElements;

    /**
     * 是否为第一页
     */
    private Boolean first;

    /**
     * 是否为最后一页
     */
    private Boolean last;

    /**
     * 查询结果是否为空
     */
    private Boolean empty;
}
