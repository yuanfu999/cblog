package com.cyf.cblog.service;

import com.cyf.cblog.entity.Blog;
import com.cyf.cblog.util.PageResult;
import com.cyf.cblog.vo.BlogDetailVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogService {


    PageResult getBlogsForIndexPage(Integer pageNum);

    List<Blog> getBlogListSortByType(Integer type);

    @Transactional
    BlogDetailVO getBlogDetailVOByBlogId(Long blogId);

    PageResult getBlogsListByTag(String tagName, Integer page);

    PageResult getBlogsPageBySearch(String keyword, Integer page);

    @Transactional
    BlogDetailVO getBlogDetailBySubUrl(String subUrl);

    Integer getBlogsCount();
}

