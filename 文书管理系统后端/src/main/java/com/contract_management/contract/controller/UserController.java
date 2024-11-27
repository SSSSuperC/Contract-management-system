package com.contract_management.contract.controller;

import com.contract_management.contract.entity.Result;
import com.contract_management.contract.entity.User;
import com.contract_management.contract.service.UserService;
import com.contract_management.contract.utils.JwtUtil;
import com.contract_management.contract.utils.Md5Util;
import com.contract_management.contract.utils.ThreadLocalUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Validated
@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //注册用户
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$")  String password)
    {
        //查询用户
        User u =  userService.findByUserName(username);

        //注册
        if(u == null)
        {
            userService.register(username,password);
            return Result.success();
        }
        //占用
        else
        {
            return Result.error("用户名已被占用 注册失败");
        }
    }


    //登录用户
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$")  String password)
    {
        //根据用户名查询用户
        User loginuser = userService.findByUserName(username);
        //判断该用户是否存在
        if(password == null)
        {
            return Result.error("密码错误");
        }
        if(loginuser == null)
        {
            return Result.error("不存在该用户");
        }
        else
        {
            //判断密码是否正确
            if(Md5Util.getMD5String(password).equals(loginuser.getPassword()))
            {

                Map<String,Object> claims = new HashMap<>();
                claims.put("id",loginuser.getId());
                claims.put("username",loginuser.getUsername());
                String token = JwtUtil.genToken(claims);//生成jwt令牌
                //注入token到redis容器
                ValueOperations<String,String> operations = stringRedisTemplate.opsForValue();
                operations.set(token,token,1, TimeUnit.HOURS);//将token作为key 和value存入redis 并设置过期时间与token过期时间一致。

                return Result.success(token);

            }

            else
                return Result.error("密码错误");
        }

    }

    @GetMapping("/userInfo")
    public Result<User> userInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }



    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatar_url) {
        userService.updateAvatar(avatar_url);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params,@RequestHeader("Authorization") String token) {
        //1.校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要的参数");
        }

        //原密码是否正确
        //调用userService根据用户名拿到原密码,再和old_pwd比对
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码填写不正确");
        }

        //newPwd和rePwd是否一样
        if (!rePwd.equals(newPwd)){
            return Result.error("两次填写的新密码不一样");
        }

        //2.调用service完成密码更新
        userService.updatePwd(newPwd);
        //删除redis中对应的token
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);
        return Result.success();
    }

}
