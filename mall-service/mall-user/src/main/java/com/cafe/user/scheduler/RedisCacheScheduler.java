package com.cafe.user.scheduler;

import com.cafe.user.facade.RoleResourceFacade;
import com.cafe.user.model.entity.RoleResource;
import com.cafe.user.service.RoleResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.scheduler
 * @Author: zhouboyi
 * @Date: 2024/12/14 21:12
 * @Description:
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class RedisCacheScheduler {

    private final RoleResourceService roleResourceService;

    private final RoleResourceFacade roleResourceFacade;

    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void refreshResourceRoleCache() {
        // 刷新缓存的时间间隔为 10 分钟
        LocalDateTime tenMinutesAgo = LocalDateTime.now().minusMinutes(10);
        // 获取 10 分钟以内有变更的 [角色-资源] 关联关系ID列表
        List<Long> resourceIds = roleResourceService.lambdaQuery()
            .ge(RoleResource::getUpdateTime, tenMinutesAgo)
            .list()
            .stream()
            .map(RoleResource::getResourceId)
            .distinct()
            .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(resourceIds)) {
            roleResourceFacade.refreshResourceRoleCache(resourceIds);
        }
        log.info("refresh resource-role redis cache, ten minutes ago: {}, resource id count: {}", tenMinutesAgo, resourceIds.size());
    }
}
