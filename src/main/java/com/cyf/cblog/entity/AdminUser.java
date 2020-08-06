package com.cyf.cblog.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 管理员表
 */
@Data
@Table(name = "admin_user")
public class AdminUser {
    public static final String COL_ADMIN_USER_ID = "admin_user_id";
    public static final String COL_LOGIN_USER_NAME = "username";
    public static final String COL_LOGIN_PASSWORD = "password";
    public static final String COL_NICK_NAME = "nick_name";
    public static final String COL_LOCKED = "locked";
    /**
     * 管理员id
     */
    @Id
    @Column(name = "admin_user_id")
    @GeneratedValue(generator = "JDBC")
    private Integer adminUserId;

    /**
     * 管理员登陆名称
     */
    @Column(name = "username")
    private String username;

    /**
     * 管理员登陆密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 管理员显示昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 是否锁定 0未锁定 1已锁定无法登陆
     */
    @Column(name = "locked")
    private Byte locked;
}