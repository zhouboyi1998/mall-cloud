package com.cafe.user.scheduler;

import com.cafe.infrastructure.redis.annotation.DistributedLock;
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
import java.util.concurrent.TimeUnit;
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

    @DistributedLock(expireTime = 15 * 60 * 1000, expireUnit = TimeUnit.MILLISECONDS)
    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void refreshResourceRoleCache() {
        // 定时任务的调度时间间隔为 10 分钟, 分布式锁的过期时间为 15 分钟, 分布式锁可能会阻塞一次定时任务的调度; 所以查询数据时, 需要覆盖两个定时任务的时间窗口 (20 分钟)
        // 定时任务的调度时间不是精确的, 需要加上一定的时间窗口重叠, 确保覆盖所有改动数据; 所以查询数据时, 还需要再加上时间窗口重叠的补偿时间 (5 分钟)
        LocalDateTime minutesAgo = LocalDateTime.now().minusMinutes(25);
        // 获取最近有变更的 [角色-资源] 关联关系ID列表
        List<Long> resourceIds = roleResourceService.lambdaQuery()
            .ge(RoleResource::getUpdateTime, minutesAgo)
            .list()
            .stream()
            .map(RoleResource::getResourceId)
            .distinct()
            .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(resourceIds)) {
            roleResourceFacade.refreshResourceRoleCache(resourceIds);
        }
        log.info("refresh resource-role redis cache, minutes ago: {}, resource id count: {}", minutesAgo, resourceIds.size());
    }
}
