package com.cyf.cblog.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 博客评论
 */
@Data
@Table(name = "blog_comment")
public class BlogComment {
    public static final String COL_COMMENT_ID = "comment_id";
    public static final String COL_BLOG_ID = "blog_id";
    public static final String COL_COMMENTATOR = "commentator";
    public static final String COL_EMAIL = "email";
    public static final String COL_WEBSITE_URL = "website_url";
    public static final String COL_COMMENT_BODY = "comment_body";
    public static final String COL_COMMENT_CREATE_TIME = "comment_create_time";
    public static final String COL_COMMENTATOR_IP = "commentator_ip";
    public static final String COL_REPLY_BODY = "reply_body";
    public static final String COL_REPLY_CREATE_TIME = "reply_create_time";
    public static final String COL_COMMENT_STATUS = "comment_status";
    public static final String COL_IS_DELETED = "is_deleted";
    /**
     * 主键id
     */
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(generator = "JDBC")
    private Long commentId;

    /**
     * 关联的blog主键
     */
    @Column(name = "blog_id")
    private Long blogId;

    /**
     * 评论者名称
     */
    @Column(name = "commentator")
    private String commentator;

    /**
     * 评论人的邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 网址
     */
    @Column(name = "website_url")
    private String websiteUrl;

    /**
     * 评论内容
     */
    @Column(name = "comment_body")
    private String commentBody;

    /**
     * 评论提交时间
     */
    @Column(name = "comment_create_time")
    private Date commentCreateTime;

    /**
     * 评论时的ip地址
     */
    @Column(name = "commentator_ip")
    private String commentatorIp;

    /**
     * 回复内容
     */
    @Column(name = "reply_body")
    private String replyBody;

    /**
     * 回复时间
     */
    @Column(name = "reply_create_time")
    private Date replyCreateTime;

    /**
     * 是否审核通过 0-未审核 1-审核通过
     */
    @Column(name = "comment_status")
    private Byte commentStatus;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;
}