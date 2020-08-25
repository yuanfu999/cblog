package com.cyf.cblog.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "user_blog_relation")
public class UserBlogRelation {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "blog_id")
    private Integer blogId;

    @Column(name = "create_time")
    private Date createTime;
}