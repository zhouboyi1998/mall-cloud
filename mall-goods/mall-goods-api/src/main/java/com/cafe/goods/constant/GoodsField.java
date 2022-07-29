package com.cafe.goods.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.constant
 * @Author: zhouboyi
 * @Date: 2022/7/29 16:57
 * @Description:
 */
public class GoodsField {

    public static final String SPU_NAME = "spuName";

    public static final String BRAND_NAME = "brandName";

    public static final String CATEGORY_NAME = "categoryName";

    public static final Map<String, String> TABLE_FIELD_MAP = new HashMap<String, String>() {{
        put(GoodsTable.SPU, SPU_NAME);
        put(GoodsTable.BRAND, BRAND_NAME);
        put(GoodsTable.CATEGORY, CATEGORY_NAME);
    }};
}
