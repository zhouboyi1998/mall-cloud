package com.cafe.goods.constant;

import com.cafe.goods.model.Brand;
import com.cafe.goods.model.Category;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.constant
 * @Author: zhouboyi
 * @Date: 2022/7/29 16:54
 * @Description:
 */
public class GoodsTableBeanMap {

    public static final Map<String, Class<?>> TABLE_BEAN_MAP = new HashMap<String, Class<?>>() {{
        put(GoodsTable.BRAND, Brand.class);
        put(GoodsTable.CATEGORY, Category.class);
    }};
}
