package com.cafe.goods.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.constant
 * @Author: zhouboyi
 * @Date: 2022/7/29 15:41
 * @Description: 商品模块数据库表
 */
public class GoodsTable {

    public static final String SKU = "mall_goods.mall_sku";

    public static final String SPU = "mall_goods.mall_spu";

    public static final String BRAND = "mall_goods.mall_brand";

    public static final String CATEGORY = "mall_goods.mall_category";

    public static final List<String> GOODS_RELATION = new ArrayList<String>() {{
        add(SPU);
        add(BRAND);
        add(CATEGORY);
    }};
}
