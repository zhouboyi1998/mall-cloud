package com.cafe.common.mybatisplus.incrementer;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.cafe.id.feign.IDFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.mybatisplus.incrementer
 * @Author: zhouboyi
 * @Date: 2022/11/1 16:59
 * @Description: MyBatis-Plus ID 生成器实现类
 */
@Component
public class IDGenerator implements IdentifierGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(IDGenerator.class);

    private final IDFeign idFeign;

    @Autowired
    public IDGenerator(IDFeign idFeign) {
        this.idFeign = idFeign;
    }

    @Override
    public Number nextId(Object entity) {
        String name = entity.getClass().getName();
        Long id = idFeign.nextId().getBody();
        LOGGER.info("IDGenerator.nextId(): Generate <{}> primary key -> {}", name, id);
        return id;
    }
}
