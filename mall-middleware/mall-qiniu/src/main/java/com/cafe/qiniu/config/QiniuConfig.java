package com.cafe.qiniu.config;

import com.qiniu.storage.Region;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.qiniu.config
 * @Author: zhouboyi
 * @Date: 2023/10/18 17:41
 * @Description:
 */
@Configuration
public class QiniuConfig {

    @Bean
    public Map<String, Region> regionMap() {
        Map<String, Region> regionMap = new HashMap<>(8);
        regionMap.put("huanan", Region.huanan());
        regionMap.put("huadong", Region.huadong());
        regionMap.put("huabei", Region.huabei());
        regionMap.put("beimei", Region.beimei());
        regionMap.put("xinjiapo", Region.xinjiapo());
        regionMap.put("huadong2", Region.huadongZheJiang2());
        return regionMap;
    }
}
