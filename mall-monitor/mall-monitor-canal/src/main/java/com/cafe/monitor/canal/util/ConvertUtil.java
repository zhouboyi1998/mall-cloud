package com.cafe.monitor.canal.util;

import com.alibaba.otter.canal.protocol.CanalEntry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.canal.util
 * @Author: zhouboyi
 * @Date: 2022/7/29 15:58
 * @Description:
 */
public class ConvertUtil {

    public static Map<String, Object> convertToMap(List<CanalEntry.Column> columnList) {
        // 存储属性名与字段值对应关系的 Map
        Map<String, Object> rowMap = new HashMap<String, Object>(16);
        for (CanalEntry.Column column : columnList) {
            rowMap.put(column.getName(), column.getValue());
        }
        return rowMap;
    }
}
