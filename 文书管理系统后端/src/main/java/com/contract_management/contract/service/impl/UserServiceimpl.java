package com.contract_management.contract.service.impl;

import com.contract_management.contract.entity.User;
import com.contract_management.contract.mapper.UserMapper;
import com.contract_management.contract.service.UserService;
import com.contract_management.contract.utils.Md5Util;
import com.contract_management.contract.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User findByUserName(String username) {
        User u = userMapper.findByUserName(username);
        return u;
    }


    @Override
    public void register(String username, String password) {
        //先加密密码
        String md5Util = Md5Util.getMD5String(password);

        //添加入数据库
        userMapper.add(username,md5Util);

    }

    @Override
    public void update(User user) {
        user.setUpdate_time(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }



}
