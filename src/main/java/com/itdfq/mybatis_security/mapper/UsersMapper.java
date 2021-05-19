package com.itdfq.mybatis_security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itdfq.mybatis_security.pojo.Users;
import org.springframework.stereotype.Repository;

/**
 * @Author GocChin
 * @Date 2021/5/19 14:13
 * @Blog: itdfq.com
 * @QQ: 909256107
 * @Descript:
 */

@Repository  //持久化
public interface UsersMapper extends BaseMapper<Users> {
}
