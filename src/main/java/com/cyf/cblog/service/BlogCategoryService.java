package com.cyf.cblog.service;

import com.cyf.cblog.entity.BlogCategory;
import com.cyf.cblog.util.PageQueryUtil;
import com.cyf.cblog.util.PageResult;

import java.util.List;

public interface BlogCategoryService  {

    List<BlogCategory> findCategoryListByIds(List<Integer> categoryIds);

    Integer getCategorysCount();

    List<BlogCategory> getAllCategories();

    PageResult getBlogCategoryPage(PageQueryUtil pageUtil);

    Boolean saveCategory(Integer categoryId, String categoryName, String categoryIcon);

    Boolean deleteCategoryIds(Integer[] ids);
}
