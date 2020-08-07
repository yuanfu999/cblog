package com.cyf.cblog.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * 博客标签关系表
 */
@Data
@Table(name = "blog_tag_relation")
public class BlogTagRelation {
    public static final String COL_RELATION_ID = "relation_id";
    public static final String COL_BLOG_ID = "blog_id";
    public static final String COL_TAG_ID = "tag_id";
    public static final String COL_CREATE_TIME = "create_time";
    /**
     * 关系表id
     */
    @Id
    @Column(name = "relation_id")
    @GeneratedValue(generator = "JDBC")
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
}