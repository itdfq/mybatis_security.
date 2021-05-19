package com.itdfq.mybatis_security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itdfq.mybatis_security.mapper.UsersMapper;
import com.itdfq.mybatis_security.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * @Author GocChin
 * @Date 2021/5/19 14:58
 * @Blog: itdfq.com
 * @QQ: 909256107
 */
@Service
public class UsersService implements UserDetailsService  {
    @Autowired
    private  UsersMapper usersMapper;
    /**
     * 通过用户名进行查询
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws
            UsernameNotFoundException {
        QueryWrapper<Users> wrapper = new QueryWrapper();
        wrapper.eq("username",s);
        Users users = usersMapper.selectOne(wrapper);
        if(users == null) {
            throw  new UsernameNotFoundException("用户名不存在！");
        }
        System.out.println(users);
        List<GrantedAuthority> auths =
                AuthorityUtils.commaSeparatedStringToAuthorityList(users.getRole());
        return new User(users.getUsername(), new BCryptPasswordEncoder().encode(users.getPassword()), auths) ;
    }
}
