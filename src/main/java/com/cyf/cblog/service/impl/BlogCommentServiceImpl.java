package com.cyf.cblog.service.impl;

import com.cyf.cblog.entity.BlogComment;
import com.cyf.cblog.mapper.BlogCommentMapper;
import com.cyf.cblog.service.BlogCommentService;
import com.cyf.cblog.util.PageQueryUtil;
import com.cyf.cblog.util.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogCommentServiceImpl implements BlogCommentService {

    @Autowired
    private BlogCommentMapper blogCommentMapper;

    @Override
    public PageResult getBlogCommentListByPage(Long blogId, Integer commentPage) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", commentPage);
        params.put("limit", 5);
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        Example example = new Example(BlogComment.class);
        Example.Criteria criteria = example.createCriteria();
        PageHelper.startPage(pageQueryUtil.getPage(), pageQueryUtil.getLimit());
        criteria.andEqualTo("isDeleted", 0).andEqualTo("blogId", blogId).andEqualTo("commentStatus", 1);
        example.setOrderByClause("comment_create_time desc");
        List<BlogComment> blogComments = blogCommentMapper.selectByExample(example);
        PageInfo<BlogComment> pageInfo = new PageInfo<>(blogComments);
        return new PageResult(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getSize(), pageInfo.getPageNum());
    }

    @Override
    public String addComment(BlogComment comment) {
        Boolean boo = blogCommentMapper.insertSelective(comment) == 1;
        if (boo){
            return "评论成功";
        }
        return "评论失败";
    }

    @Override
    public Long getCommentsCount() {
        Example example = new Example(BlogComment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", 0).andEqualTo("commentStatus", 1);
        return (long)blogCommentMapper.selectCountByExample(example);
    }
}

