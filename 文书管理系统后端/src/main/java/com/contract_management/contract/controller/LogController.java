package com.contract_management.contract.controller;

import com.contract_management.contract.entity.Category;
import com.contract_management.contract.entity.Log;
import com.contract_management.contract.entity.Result;
import com.contract_management.contract.service.LogService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController
{
    @Autowired
    private LogService logService;

    @GetMapping
    public Result<List<Log>> list(){
        List<Log> cs = logService.list();
        return Result.success(cs);
    }

    @DeleteMapping
    public Result Delete(){
        logService.Delete();
        return Result.success();
    }

    @GetMapping("/insert")
    public Result Insert(
                        @RequestParam(required = false) Integer category_id,
                        @RequestParam(required = false) String category_name,
                        @RequestParam(required = false) String location,
                        @RequestParam(required = false) String state,
                        @RequestParam(required = false) String title
                        )
    {
        logService.Insert(category_id,state,title,location,category_name);
        return Result.success();
    }

}
