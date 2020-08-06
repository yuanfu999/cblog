package com.cyf.cblog.service;

import com.cyf.cblog.entity.AdminUser;
import org.springframework.transaction.annotation.Transactional;

public interface AdminUserService {


    AdminUser login(String userName, String password);

    AdminUser getAdminUserById(Integer loginUserId);

    @Transactional
    Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword);

    Boolean updateName(Integer loginUserId, String loginUserName, String nickName);
}

