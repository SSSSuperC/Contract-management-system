package com.contract_management.contract.service;

import com.contract_management.contract.entity.Log;

import java.util.List;

public interface LogService {
    List<Log> list();

    void Delete();


    void Insert(Integer category_id, String state, String title, String location, String category_name);
}
