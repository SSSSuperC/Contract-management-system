package com.contract_management.contract.mapper;

import com.contract_management.contract.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper
{
    @Select("SELECT * FROM user WHERE username = #{username}")
    public User findByUserName(String username);


    @Insert("INSERT INTO user (username, password, create_time, update_time) VALUES (#{username},#{md5Util},now(),now())")
    void add(String username, String md5Util);

    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{update_time} where id=#{id}")
    void update(User user);

    @Update("update user set userPic=#{avatarUrl},update_time=now() where id=#{id}")
    void updateAvatar(String avatarUrl,Integer id);

    @Update("update user set password=#{md5String},update_time=now() where id=#{id}")
    void updatePwd(String md5String, Integer id);


}
