package com.cyf.cblog.service.impl;

import com.cyf.cblog.entity.BlogCategory;
import com.cyf.cblog.mapper.BlogCategoryMapper;
import com.cyf.cblog.service.BlogCategoryService;
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

    @Override
    public List<BlogCategory> getAllCategories() {
        Example example = new Example(BlogCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", 0);
        return blogCategoryMapper.selectByExample(example);
    }

    @Override
    public PageResult getBlogCategoryPage(PageQueryUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(), pageUtil.getLimit());
        Example example = new Example(BlogCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", 0);
        List<BlogCategory> categoryList = blogCategoryMapper.selectByExample(example);
        PageInfo<BlogCategory> pageInfo = new PageInfo<>(categoryList);
        return new PageResult(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPageSize(),pageInfo.getPageNum());
    }

    @Override
    public Boolean saveCategory(Integer categoryId, String categoryName, String categoryIcon) {
        if (categoryId == -1 || categoryId == null){
            BlogCategory category = blogCategoryMapper.findCategoryByName(categoryName);
            if (category == null){
                BlogCategory blogCategory = new BlogCategory();
                blogCategory.setCategoryName(categoryName);
                blogCategory.setCategoryIcon(categoryIcon);
                blogCategory.setCreateTime(new Date());
                return blogCategoryMapper.insertSelective(blogCategory) == 1;
            }
        }else{
            BlogCategory blogCategory = blogCategoryMapper.selectByPrimaryKey(categoryId);
            if (blogCategory != null){
                blogCategory.setCategoryName(categoryName);
                blogCategory.setCategoryIcon(categoryIcon);
                return blogCategoryMapper.updateByPrimaryKey(blogCategory) == 1;
            }
        }
        return false;
    }

    @Override
    public Boolean deleteCategoryIds(Integer[] ids) {
        return blogCategoryMapper.deleteCategoryByIds(ids) > 0;
    }
}
