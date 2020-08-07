package com.cyf.cblog.mapper;

import com.cyf.cblog.entity.Blog;
import com.cyf.cblog.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BlogMapper extends Mapper<Blog> {


    List<Blog> findBlogList(PageQueryUtil pageQueryUtil);

    List<Blog> findBlogListSortByType(@Param("type") int type, @Param("limit")int limit);

    List<Blog> findBlogsListByTag(PageQueryUtil pageQueryUtil);

    //物理删除
    Integer deleteBlogByIdsForPhysical(Integer[] ids);

    //逻辑删除
    Integer deleteBlogByIdsForLogic(Integer[] ids);
}