package com.cyf.cblog.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.cyf.cblog.mapper.UserBlogRelationMapper;
import com.cyf.cblog.service.UserBlogRelationService;
@Service
public class UserBlogRelationServiceImpl implements UserBlogRelationService{

    @Resource
    private UserBlogRelationMapper userBlogRelationMapper;

}
