package com.contract_management.contract.service.impl;

import com.contract_management.contract.entity.Business;
import com.contract_management.contract.mapper.BusinessMapper;
import com.contract_management.contract.service.BusinessService;
import com.contract_management.contract.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    BusinessMapper businessMapper;

    @Override
    public List<Business> list() {
        return businessMapper.list();
    }
}
