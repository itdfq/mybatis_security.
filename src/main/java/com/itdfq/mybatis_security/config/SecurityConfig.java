package com.itdfq.mybatis_security.config;


import com.itdfq.mybatis_security.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author GocChin
 * @Date 2021/5/19 14:55
 * @Blog: itdfq.com
 * @QQ: 909256107
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsersService usersService;

    /**
     * 授权的
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*
        //退出
        http.logout().logoutUrl("/logout").
                logoutSuccessUrl("/test/hello").permitAll();

        //配置没有权限访问跳转自定义页面
        http.exceptionHandling().accessDeniedPage("/unauth.html");
        http.formLogin()   //自定义自己编写的登录页面
                .loginPage("/on.html")  //登录页面设置
                .loginProcessingUrl("/user/login")   //登录访问路径
                .defaultSuccessUrl("/success.html").permitAll()  //登录成功之后，跳转路径
                .failureUrl("/unauth.html")
                .and().authorizeRequests()
                .antMatchers("/","/test/hello","/user/login").permitAll() //设置哪些路径可以直接访问，不需要认证
                //当前登录用户，只有具有admins权限才可以访问这个路径
                //1 hasAuthority方法
                // .antMatchers("/test/index").hasAuthority("admins")
                //2 hasAnyAuthority方法
                // .antMatchers("/test/index").hasAnyAuthority("admins,manager")
                //3 hasRole方法   ROLE_sale
                .antMatchers("/test/index").hasRole("sale")

                .anyRequest().authenticated()
                .and().rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60)//设置有效时长，单位秒
                .userDetailsService(userDetailsService);
        // .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        // .and().csrf().disable();  //关闭csrf防护
*/
        //首页所有人可以访问，功能页有相应权限才能访问
        //链式编程
        //请求授权的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasAuthority("1")
                .antMatchers("/level2/**").hasAuthority("2")
                .antMatchers("/level3/**").hasAuthority("3");
        //没有权限，默认到登录页面
        http.formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").loginProcessingUrl("/login");
        //防止网站攻击
        http.csrf().disable();//登出可能存在失败的原因
        //注销功能
        http.logout().logoutSuccessUrl("/login");
        //开启记住我功能
        http.rememberMe().rememberMeParameter("remember")
                .tokenValiditySeconds(600);//设置有效时长，单位秒;
    }
    /**
     * 认证的
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.getDefaultUserDetailsService()
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("cjh").password(new BCryptPasswordEncoder().encode("123123")).roles("vip2","vip3")
//                .and()
//                .withUser("root").password(new BCryptPasswordEncoder().encode("123123")).roles("vip1","vip2","vip3")
//                .and()
//                .withUser("guest").password(new BCryptPasswordEncoder().encode("123123")).roles("vip1");

        //There is no PasswordEncoder mapped for the id "null"
        auth.userDetailsService(usersService).passwordEncoder(password());
    }
    private PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }
}
