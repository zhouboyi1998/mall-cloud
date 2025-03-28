package com.cafe.elasticsearch.model.index;

import com.cafe.common.constant.elasticsearch.ElasticSearchConstant;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.model.index
 * @Author: zhouboyi
 * @Date: 2024/6/28 16:18
 * @Description: 订单全文索引
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OrderIndex", description = "订单全文索引")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Document(indexName = ElasticSearchConstant.Order.INDEX)
public class OrderIndex implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单ID")
    @Id
    @Field(type = FieldType.Long)
    private Long id;

    @ApiModelProperty(value = "订单号")
    @Field(type = FieldType.Text, analyzer = "standard")
    private String orderNo;

    @ApiModelProperty(value = "客户名称")
    @Field(type = FieldType.Text, analyzer = "standard")
    private String customerName;

    @ApiModelProperty(value = "订单状态")
    @Field(type = FieldType.Integer)
    private Integer status;

    @ApiModelProperty(value = "订单金额")
    @Field(type = FieldType.Double)
    private Double amount;

    @ApiModelProperty(value = "创建时间")
    @Field(type = FieldType.Date)
    private String createTime;
} 