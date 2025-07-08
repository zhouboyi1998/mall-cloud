package com.cafe.id.service;

import com.cafe.common.enumeration.http.HttpStatusEnum;
import com.cafe.common.util.base.StringUtil;
import com.cafe.id.property.IDProperties;
import com.cafe.starter.boot.model.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.service
 * @Author: zhouboyi
 * @Date: 2025/7/8 17:45
 * @Description:
 */
@RequiredArgsConstructor
@Component
public class IDServiceStrategy {

    /**
     * 分布式ID配置
     */
    private final IDProperties idProperties;

    /**
     * 分布式ID业务实现类列表
     */
    private final List<IDService> idServiceList;

    /**
     * 分布式ID业务实现类映射
     */
    private Map<String, IDService> idServiceMap;

    @PostConstruct
    public void initIDServiceMap() {
        // 组装分布式ID业务实现类映射
        idServiceMap = idServiceList.stream()
            .collect(Collectors.toMap(idService -> StringUtil.lowerCaseFirstLetter(idService.getClass().getSimpleName()), Function.identity()));
    }

    /**
     * 通过名称前缀获取分布式ID业务实现类
     *
     * @param generator 分布式ID生成器
     * @return 业务实现类
     */
    public IDService idService(String generator) {
        String serviceName = Optional.ofNullable(IDProperties.Generator.getGeneratorByName(generator))
            .orElse(idProperties.getDefaultGenerator())
            .getServiceName();
        if (!idServiceMap.containsKey(serviceName)) {
            throw new BusinessException(HttpStatusEnum.ID_SERVICE_IMPL_NOT_FOUND);
        }
        return idServiceMap.get(serviceName);
    }
}
