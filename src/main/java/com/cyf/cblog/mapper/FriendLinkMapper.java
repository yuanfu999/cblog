package com.cyf.cblog.mapper;

import com.cyf.cblog.entity.FriendLink;
import tk.mybatis.mapper.common.Mapper;

public interface FriendLinkMapper extends Mapper<FriendLink> {

    Integer deleteByIds(Integer[] ids);
}