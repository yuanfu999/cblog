package com.cyf.cblog.service.impl;

import com.cyf.cblog.entity.FriendLink;
import com.cyf.cblog.mapper.FriendLinkMapper;
import com.cyf.cblog.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FriendLinkServiceImpl implements FriendLinkService {

    @Autowired
    private FriendLinkMapper friendLinkMapper;

    @Override
    public Map<Byte, List<FriendLink>> getLinkListByPage() {
        List<FriendLink> friendLinks = friendLinkMapper.selectAll();
        Map<Byte, List<FriendLink>> friendLinkGroup = null;
        if (!CollectionUtils.isEmpty(friendLinks)){
            friendLinkGroup = friendLinks.stream().collect(Collectors.groupingBy(FriendLink::getLinkType));
        }
        return friendLinkGroup;
    }

    @Override
    public Integer getFriendLinksCount() {
        Example example = new Example(FriendLink.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", 0);
        return friendLinkMapper.selectCountByExample(example);
    }
}

