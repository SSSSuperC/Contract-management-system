package com.contract_management.contract.controller;

import com.contract_management.contract.entity.Business;
import com.contract_management.contract.entity.Result;
import com.contract_management.contract.service.BusinessService;
import com.contract_management.contract.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @GetMapping
    public Result<List<Business>>list(){
        List<Business> cs = businessService.list();
        return Result.success(cs);
    }
}
