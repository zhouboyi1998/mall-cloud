package com.cafe.common.mybatisplus.incrementer;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.cafe.id.feign.IDFeign;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.mybatisplus.incrementer
 * @Author: zhouboyi
 * @Date: 2022/11/1 16:59
 * @Description: MyBatis-Plus ID 生成器实现类
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class IDGenerator implements IdentifierGenerator {

    private final IDFeign idFeign;

    @Override
    public Number nextId(Object entity) {
        String name = entity.getClass().getName();
        Long id = idFeign.nextId().getBody();
        log.info("IDGenerator.nextId(): Generate <{}> primary key -> {}", name, id);
        return id;
    }
}
