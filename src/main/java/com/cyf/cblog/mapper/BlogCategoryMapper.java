package com.cyf.cblog.mapper;

import com.cyf.cblog.entity.BlogCategory;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BlogCategoryMapper extends Mapper<BlogCategory>{

    List<BlogCategory> findCategoryListByIds(@Param("categoryIds") List<Integer> categoryIds);
}