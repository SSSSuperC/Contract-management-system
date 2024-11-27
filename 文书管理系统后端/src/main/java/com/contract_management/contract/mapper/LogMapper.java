package com.contract_management.contract.mapper;

import com.contract_management.contract.entity.Category;
import com.contract_management.contract.entity.Log;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface LogMapper {

    //查询所有
    @Select("select * from log where create_user = #{userId}")
    List<Log> list(Integer userId);

    //根据id删除
    @Delete("delete from log")
    void Delete();

    void Insert(Integer category_id, String state, String title, String location, String category_name, Integer create_user, LocalDateTime search_time);
}
