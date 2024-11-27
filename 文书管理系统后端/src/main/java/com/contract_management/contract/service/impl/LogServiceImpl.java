package com.contract_management.contract.service.impl;

import com.contract_management.contract.entity.Category;
import com.contract_management.contract.mapper.LogMapper;
import com.contract_management.contract.service.LogService;
import com.contract_management.contract.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import com.contract_management.contract.entity.Log;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    @Override
    public List<Log> list() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        return logMapper.list(userId);
    }

    @Override
    public void Delete()
    {
        logMapper.Delete();
    }

    @Override
    public void Insert(Integer category_id, String state, String title, String location, String category_name) {
        //获取用户id
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        //获取查询时间
        LocalDateTime search_time = LocalDateTime.now();
        logMapper.Insert(category_id,state,title,location,category_name,userId,search_time);
    }


}
