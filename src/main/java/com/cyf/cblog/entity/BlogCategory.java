package com.cyf.cblog.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
    * 博客分类表
    */
@Data
@Table(name = "blog_category")
public class BlogCategory {

    public BlogCategory() { }
    public BlogCategory(Integer categoryId){
        this.categoryId = categoryId;
    }
    /**
     * 分类表主键
     */
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    /**
     * 分类的名称
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 分类的图标
     */
    @Column(name = "category_icon")
    private String categoryIcon;

    /**
     * 分类的排序值 被使用的越多数值越大
     */
    @Column(name = "category_rank")
    private Integer categoryRank;

    /**
     * 是否删除 0=否 1=是
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    public static final String COL_CATEGORY_ID = "category_id";

    public static final String COL_CATEGORY_NAME = "category_name";

    public static final String COL_CATEGORY_ICON = "category_icon";

    public static final String COL_CATEGORY_RANK = "category_rank";

    public static final String COL_IS_DELETED = "is_deleted";

    public static final String COL_CREATE_TIME = "create_time";
}