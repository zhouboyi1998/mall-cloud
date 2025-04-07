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
 * @Date: 2024/7/31 10:19
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

    @ApiModelProperty(value = "订单编号")
    @Field(type = FieldType.Long)
    private Long orderNo;

    @ApiModelProperty(value = "收货地址快照")
    @Field(type = FieldType.Text)
    private String address;

    @ApiModelProperty(value = "收货人快照")
    @Field(type = FieldType.Keyword)
    private String receiver;

    @ApiModelProperty(value = "收货人手机号快照")
    @Field(type = FieldType.Keyword)
    private String mobile;
}
