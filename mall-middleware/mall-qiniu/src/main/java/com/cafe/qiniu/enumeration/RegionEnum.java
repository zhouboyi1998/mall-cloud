package com.cafe.qiniu.enumeration;

import com.qiniu.storage.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.qiniu.enumeration
 * @Author: zhouboyi
 * @Date: 2023/10/19 17:15
 * @Description: 七牛云存储区域枚举
 */
@Getter
@AllArgsConstructor
public enum RegionEnum {

    /**
     * 华东-浙江
     */
    HUADONG("huadong", Region.huadong()),

    /**
     * 华北-河北
     */
    HUABEI("huabei", Region.huabei()),

    /**
     * 华南-广东
     */
    HUANAN("huanan", Region.huanan()),

    /**
     * 北美-洛杉矶
     */
    BEIMEI("beimei", Region.beimei()),

    /**
     * 亚太-新加坡
     */
    XINJIAPO("xinjiapo", Region.xinjiapo()),

    /**
     * 华东-浙江2
     */
    HUADONG2("huadong2", Region.huadongZheJiang2());

    /**
     * 存储区域名
     */
    private final String name;

    /**
     * 存储区域对象
     */
    private final Region region;

    public static Region getRegionByName(String name) {
        RegionEnum[] regionList = values();
        for (RegionEnum region : regionList) {
            if (Objects.equals(region.getName(), name)) {
                return region.getRegion();
            }
        }
        return HUADONG.getRegion();
    }
}
