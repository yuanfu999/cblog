package com.cyf.cblog.mapper;

import com.cyf.cblog.entity.BlogTag;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BlogTagMapper extends Mapper<BlogTag> {

    List<BlogTag> findHotBlogTag();

    List<BlogTag> findBlogTagByBlogId(@Param("blogId") Long blogId);

    void insertBlogTagList(List<BlogTag> newTags);

    Integer deleteTagByIds(Integer[] ids);
}