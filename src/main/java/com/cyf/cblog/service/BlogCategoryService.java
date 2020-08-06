package com.cyf.cblog.service;

import com.cyf.cblog.entity.BlogCategory;

import java.util.List;

public interface BlogCategoryService  {

    List<BlogCategory> findCategoryListByIds(List<Integer> categoryIds);

    Integer getCategorysCount();
}
