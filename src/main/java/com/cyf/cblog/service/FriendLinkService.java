package com.cyf.cblog.service;

import com.cyf.cblog.entity.FriendLink;

import java.util.List;
import java.util.Map;

public interface FriendLinkService {


    Map<Byte, List<FriendLink>> getLinkListByPage();

    Integer getFriendLinksCount();
}

