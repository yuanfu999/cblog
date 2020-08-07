package com.cyf.cblog.mapper;

import com.cyf.cblog.entity.BlogTag;
import com.cyf.cblog.entity.BlogTagRelation;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BlogTagRelationMapper extends Mapper<BlogTagRelation> {

    Integer insertBlogTagRelationList(List<BlogTagRelation> blogTagRelations);

    void deleteBlogTagRelationList(List<BlogTag> allTagsForUpdate);
}