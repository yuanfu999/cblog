package com.cyf.cblog.service;

import com.cyf.cblog.entity.BlogComment;
import com.cyf.cblog.util.PageResult;
import org.springframework.transaction.annotation.Transactional;

public interface BlogCommentService {


    PageResult getBlogCommentListByPage(Long blogId, Integer commentPage);

    @Transactional
    String addComment(BlogComment comment);

    Long getCommentsCount();
}

