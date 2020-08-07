package com.cyf.cblog.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.cyf.cblog.mapper.BlogTagRelationMapper;
import com.cyf.cblog.service.BlogTagRelationService;
@Service
public class BlogTagRelationServiceImpl implements BlogTagRelationService{

    @Resource
    private BlogTagRelationMapper blogTagRelationMapper;

}
