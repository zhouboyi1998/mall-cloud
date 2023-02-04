package com.cafe.member.dao;

import com.cafe.member.model.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.member.dao
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 会员数据访问接口
 */
@Mapper
public interface MemberMapper extends BaseMapper<Member> {

}
