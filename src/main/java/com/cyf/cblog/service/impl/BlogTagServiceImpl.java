package com.cyf.cblog.service.impl;

import com.cyf.cblog.entity.BlogTag;
import com.cyf.cblog.mapper.BlogTagMapper;
import com.cyf.cblog.service.BlogTagService;
import com.cyf.cblog.util.PageQueryUtil;
import com.cyf.cblog.util.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
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

    @Override
    public PageResult getBlogTagPage(PageQueryUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(), pageUtil.getLimit());
        Example example = new Example(BlogTag.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", 0);
        List<BlogTag> blogTags = blogTagMapper.selectByExample(example);
        PageInfo<BlogTag> pageInfo = new PageInfo<>(blogTags);
        return new PageResult(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPageSize(), pageInfo.getPageNum());
    }

    @Override
    public Boolean saveTag(String tagName) {
        Example example = new Example(BlogTag.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", 0).andEqualTo("tagName", tagName);
        BlogTag blogTag = blogTagMapper.selectOneByExample(example);
        if (blogTag == null) {
            BlogTag tag = new BlogTag();
            tag.setTagName(tagName);
            tag.setUseNum(1L);
            tag.setCreateTime(new Date());
            return blogTagMapper.insertSelective(tag) > 0;
        }
        return false;
    }

    @Override
    public Boolean deleteTagByIds(Integer[] ids) {
        return blogTagMapper.deleteTagByIds(ids) > 0;
    }
}
