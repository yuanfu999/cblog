package com.cyf.cblog.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
    * 系统配置
    */
@Data
@Table(name = "sys_config")
public class SysConfig {
    /**
     * 配置项的名称
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String configName;

    /**
     * 配置项的值
     */
    @Column(name = "config_value")
    private String configValue;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    public static final String COL_CONFIG_NAME = "config_name";

    public static final String COL_CONFIG_VALUE = "config_value";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}