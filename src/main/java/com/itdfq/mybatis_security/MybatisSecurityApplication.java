package com.itdfq.mybatis_security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.itdfq.mybatis_security.mapper")
@SpringBootApplication
public class MybatisSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisSecurityApplication.class, args);
    }

}
