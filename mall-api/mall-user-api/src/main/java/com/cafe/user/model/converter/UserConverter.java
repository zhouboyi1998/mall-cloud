package com.cafe.user.model.converter;

import com.cafe.user.model.entity.User;
import com.cafe.user.model.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.model.converter
 * @Author: zhouboyi
 * @Date: 2024/12/26 17:33
 * @Description: 用户模型转换器
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    /**
     * 实体模型 -> 视图模型
     *
     * @param user 实体模型
     * @return 视图模型
     */
    UserVO toVO(User user);
}
