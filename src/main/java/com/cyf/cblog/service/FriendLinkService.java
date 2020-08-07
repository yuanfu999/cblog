package com.cyf.cblog.service;

import com.cyf.cblog.entity.FriendLink;
import com.cyf.cblog.util.PageQueryUtil;
import com.cyf.cblog.util.PageResult;

import java.util.List;
import java.util.Map;

public interface FriendLinkService {


    Map<Byte, List<FriendLink>> getLinkListByPage();

    Integer getFriendLinksCount();

    PageResult getBlogLinkPage(PageQueryUtil pageUtil);

    Boolean saveLink(FriendLink link);

    FriendLink selectById(Integer id);

    Boolean deleteFriendLinkByIds(Integer[] ids);
}

