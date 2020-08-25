package com.cyf.cblog.mapper;

import com.cyf.cblog.entity.Blog;
import com.cyf.cblog.entity.UserBlogRelation;
import com.cyf.cblog.util.PageQueryUtil;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserBlogRelationMapper extends Mapper<UserBlogRelation> {

    List<Blog> findBlogByUserId(PageQueryUtil pageQueryUtil);
}