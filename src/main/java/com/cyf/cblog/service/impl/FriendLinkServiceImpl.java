package com.cyf.cblog.service.impl;

import com.cyf.cblog.entity.FriendLink;
import com.cyf.cblog.mapper.FriendLinkMapper;
import com.cyf.cblog.service.FriendLinkService;
import com.cyf.cblog.util.PageQueryUtil;
import com.cyf.cblog.util.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        Example example = new Example(FriendLink.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", 0);
        List<FriendLink> friendLinks = friendLinkMapper.selectByExample(example);
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

    @Override
    public PageResult getBlogLinkPage(PageQueryUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(), pageUtil.getLimit());
        Example example = new Example(FriendLink.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", 0);
        List<FriendLink> friendLinks = friendLinkMapper.selectByExample(example);
        PageInfo<FriendLink> pageInfo = new PageInfo<>(friendLinks);
        return new PageResult(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPageSize(), pageInfo.getPageNum());
    }

    @Override
    public Boolean saveLink(FriendLink link) {
        if (link.getLinkId() == -1 || link.getLinkId() == null){
            link.setLinkId(null);
            return friendLinkMapper.insertSelective(link) > 0;
        }else{
            FriendLink friendLink = friendLinkMapper.selectByPrimaryKey(link.getLinkId());
            if (friendLink != null){
                return friendLinkMapper.updateByPrimaryKeySelective(link) > 0;
            }
        }
        return false;
    }

    @Override
    public FriendLink selectById(Integer id) {
        return friendLinkMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean deleteFriendLinkByIds(Integer[] ids) {
        return friendLinkMapper.deleteByIds(ids) > 0;
    }
}

