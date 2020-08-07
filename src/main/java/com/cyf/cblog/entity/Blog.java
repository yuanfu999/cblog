package com.cyf.cblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 博客表
 */
@Data
@Table(name = "blog")
public class Blog {

    public Blog() { }
    public Blog(Long blogId){
        this.blogId = blogId;
    }
    /**
     * 博客表主键id
     */
    @Id
    private Long blogId;

    /**
     * 博客标题
     */
    @Column(name = "blog_title")
    private String blogTitle;

    /**
     * 博客自定义路径url
     */
    @Column(name = "blog_sub_url")
    private String blogSubUrl;

    /**
     * 博客封面图
     */
    @Column(name = "blog_cover_image")
    private String blogCoverImage;

    /**
     * 博客内容
     */
    @Column(name = "blog_content")
    private String blogContent;

    /**
     * 博客分类id
     */
    @Column(name = "blog_category_id")
    private Integer blogCategoryId;

    /**
     * 博客分类(冗余字段)
     */
    @Column(name = "blog_category_name")
    private String blogCategoryName;

    /**
     * 博客标签
     */
    @Column(name = "blog_tags")
    private String blogTags;

    /**
     * 0-草稿 1-发布
     */
    @Column(name = "blog_status")
    private Byte blogStatus;

    /**
     * 阅读量
     */
    @Column(name = "blog_views")
    private Long blogViews;

    /**
     * 0-允许评论 1-不允许评论
     */
    @Column(name = "enable_comment")
    private Byte enableComment;

    /**
     * 是否删除 0=否 1=是
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    /**
     * 添加时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public static final String COL_BLOG_ID = "blog_id";

    public static final String COL_BLOG_TITLE = "blog_title";

    public static final String COL_BLOG_SUB_URL = "blog_sub_url";

    public static final String COL_BLOG_COVER_IMAGE = "blog_cover_image";

    public static final String COL_BLOG_CONTENT = "blog_content";

    public static final String COL_BLOG_CATEGORY_ID = "blog_category_id";

    public static final String COL_BLOG_CATEGORY_NAME = "blog_category_name";

    public static final String COL_BLOG_TAGS = "blog_tags";

    public static final String COL_BLOG_STATUS = "blog_status";

    public static final String COL_BLOG_VIEWS = "blog_views";

    public static final String COL_ENABLE_COMMENT = "enable_comment";

    public static final String COL_IS_DELETED = "is_deleted";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}