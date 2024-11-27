package com.contract_management.contract.controller;

import com.contract_management.contract.entity.Result;
import com.contract_management.contract.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/contract")
public class ContractController
{
    @GetMapping("/list")
    public Result<String> list()
    {
//        try
//        {
//            Map<String , Object> claims = JwtUtil.parseToken(token);
//            return Result.success("所有的契约数据");
//        }
//        catch (Exception e)
//        {
//            response.setStatus(401);
//            return Result.error("获取token失败");
//        }
        return Result.success("所有的契约数据");

    }
}
