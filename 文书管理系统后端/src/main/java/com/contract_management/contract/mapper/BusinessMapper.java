package com.contract_management.contract.mapper;

import com.contract_management.contract.entity.Business;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BusinessMapper {
    //查询所有
    @Select("select * from business")
    List<Business> list();
}
