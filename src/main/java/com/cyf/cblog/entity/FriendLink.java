package com.cyf.cblog.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * 友链表
 */
@Data
@Table(name = "friend_link")
public class FriendLink {
    public static final String COL_LINK_ID = "link_id";
    public static final String COL_LINK_TYPE = "link_type";
    public static final String COL_LINK_NAME = "link_name";
    public static final String COL_LINK_URL = "link_url";
    public static final String COL_LINK_DESCRIPTION = "link_description";
    public static final String COL_LINK_RANK = "link_rank";
    public static final String COL_IS_DELETED = "is_deleted";
    public static final String COL_CREATE_TIME = "create_time";
    /**
     * 友链表主键id
     */
    @Id
    @Column(name = "link_id")
    @GeneratedValue(generator = "JDBC")
    private Integer linkId;

    /**
     * 友链类别 0-友链 1-推荐 2-个人网站
     */
    @Column(name = "link_type")
    private Byte linkType;

    /**
     * 网站名称
     */
    @Column(name = "link_name")
    private String linkName;

    /**
     * 网站链接
     */
    @Column(name = "link_url")
    private String linkUrl;

    /**
     * 网站描述
     */
    @Column(name = "link_description")
    private String linkDescription;

    /**
     * 用于列表排序
     */
    @Column(name = "link_rank")
    private Integer linkRank;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    /**
     * 添加时间
     */
    @Column(name = "create_time")
    private Date createTime;
}