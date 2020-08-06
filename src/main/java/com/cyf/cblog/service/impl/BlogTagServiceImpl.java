package com.cyf.cblog.service.impl;

import com.cyf.cblog.entity.BlogTag;
import com.cyf.cblog.mapper.BlogTagMapper;
import com.cyf.cblog.service.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class BlogTagServiceImpl  implements BlogTagService{

    @Autowired
    private BlogTagMapper blogTagMapper;

    @Override
    public List<BlogTag> getHotBlogTag() {
        List<BlogTag> hotBlogTag = blogTagMapper.findHotBlogTag();
        return hotBlogTag;
    }

    @Override
    public List<BlogTag> getBlogTagByBlogId(Long blogId) {
        return blogTagMapper.findBlogTagByBlogId(blogId);
    }

    @Override
    public Integer getBlogTagsCount() {
        Example example = new Example(BlogTag.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", 0);
        return blogTagMapper.selectCountByExample(example);
    }
}
