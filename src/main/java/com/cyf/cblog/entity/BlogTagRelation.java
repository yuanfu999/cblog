package com.cyf.cblog.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
    * 博客标签关系表
    */
@Data
@Table(name = "blog_tag_relation")
public class BlogTagRelation {
    /**
     * 关系表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long relationId;

    /**
     * 博客id
     */
    @Column(name = "blog_id")
    private Long blogId;

    /**
     * 标签id
     */
    @Column(name = "tag_id")
    private Integer tagId;

    /**
     * 添加时间
     */
    @Column(name = "create_time")
    private Date createTime;

    public static final String COL_RELATION_ID = "relation_id";

    public static final String COL_BLOG_ID = "blog_id";

    public static final String COL_TAG_ID = "tag_id";

    public static final String COL_CREATE_TIME = "create_time";
}