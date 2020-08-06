package com.cyf.cblog.service.impl;

import com.cyf.cblog.entity.AdminUser;
import com.cyf.cblog.mapper.AdminUserMapper;
import com.cyf.cblog.service.AdminUserService;
import com.cyf.cblog.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(String userName, String password) {
        Example example = new Example(AdminUser.class);
        Example.Criteria criteria = example.createCriteria();
        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
        criteria.andEqualTo("username", userName).andEqualTo("password", passwordMd5).andEqualTo("locked", 0);
        return adminUserMapper.selectOneByExample(example);
    }

    @Override
    public AdminUser getAdminUserById(Integer loginUserId) {
        return adminUserMapper.selectByPrimaryKey(loginUserId);
    }

    @Override
    public Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) {
        AdminUser admin = getAdminUserById(loginUserId);
        if(admin != null){
            String originalPasswordMD5 = MD5Util.MD5Encode(originalPassword, "UTF-8");
            String newPasswordMD5 = MD5Util.MD5Encode(newPassword, "UTF-8");
            if(originalPasswordMD5.equals(admin.getPassword())){
                admin.setPassword(newPasswordMD5);
                return adminUserMapper.updateByPrimaryKeySelective(admin) == 1;
            }
        }
        return false;
    }

    @Override
    public Boolean updateName(Integer loginUserId, String loginUserName, String nickName) {
        AdminUser admin = getAdminUserById(loginUserId);
        if (admin != null){
            admin.setUsername(loginUserName);
            admin.setNickName(nickName);
            return adminUserMapper.updateByPrimaryKeySelective(admin) == 1;
        }
        return false;
    }
}

