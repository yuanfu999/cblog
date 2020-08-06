package com.cyf.cblog.service.impl;

import com.cyf.cblog.entity.BlogCategory;
import com.cyf.cblog.mapper.BlogCategoryMapper;
import com.cyf.cblog.service.BlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BlogCategoryServiceImpl  implements BlogCategoryService{

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

    @Override
    public List<BlogCategory> findCategoryListByIds(List<Integer> categoryIds) {
        return blogCategoryMapper.findCategoryListByIds(categoryIds);
    }

    @Override
    public Integer getCategorysCount() {
        Example example = new Example(BlogCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", 0);
        return blogCategoryMapper.selectCountByExample(example);
    }
}
