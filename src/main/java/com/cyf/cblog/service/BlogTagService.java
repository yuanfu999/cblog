package com.cyf.cblog.service;

import com.cyf.cblog.entity.BlogTag;

import java.util.List;

public interface BlogTagService {


    List<BlogTag> getHotBlogTag();

    List<BlogTag> getBlogTagByBlogId(Long blogId);

    Integer getBlogTagsCount();
}
