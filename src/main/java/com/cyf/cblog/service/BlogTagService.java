package com.cyf.cblog.service;

import com.cyf.cblog.entity.BlogTag;
import com.cyf.cblog.util.PageQueryUtil;
import com.cyf.cblog.util.PageResult;

import java.util.List;

public interface BlogTagService {


    List<BlogTag> getHotBlogTag();

    List<BlogTag> getBlogTagByBlogId(Long blogId);

    Integer getBlogTagsCount();

    PageResult getBlogTagPage(PageQueryUtil pageUtil);

    Boolean saveTag(String tagName);

    Boolean deleteTagByIds(Integer[] ids);
}
