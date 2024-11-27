package com.contract_management.contract.service;

import com.contract_management.contract.entity.User;

public interface UserService
{


    //查询用户名
    User findByUserName(String username);

    //注册
    void register(String username, String password);

    //更新
    void update(User user);

    //更新头像
    void updateAvatar(String avatarUrl);

    //更新密码
    void updatePwd(String newPwd);

}
