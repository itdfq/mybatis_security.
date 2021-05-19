package com.itdfq.mybatis_security.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * (Users)实体类
 *
 * @author GodChin
 * @since 2021-05-19 14:12:46
 */
@Data
public class Users implements Serializable {
    private static final long serialVersionUID = -72355477030949808L;
    
    private Integer id;
    
    private String username;
    
    private String password;
    
    private String role;
    
    private String name;




}