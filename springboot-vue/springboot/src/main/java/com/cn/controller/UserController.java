package com.cn.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.common.Result;
import com.cn.entity.User;
import com.cn.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/login")
    public Result<?> login(@RequestBody User user){
        User res = userService.selectOne(Wrappers.<User>lambdaQuery().eq(User::getName,user.getName())
        .eq(User::getPassword,user.getPassword())
        );
        if (res == null){
            return Result.error("-1","用户名或密码错误");
        }
        return Result.success(res);
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user){
        User res = userService.selectOne(Wrappers.<User>lambdaQuery().eq(User::getName,user.getName()));
        if (res != null){
            return Result.error("-1","用户名重复");
        }
        if (user.getPassword() == null){
            user.setPassword("123456");
        }
        userService.insert(user);
        return Result.success();
    }


    @PostMapping
    public Result<?> save(@RequestBody User user){
        if (user.getPassword() == null){
            user.setPassword("123456");
        }
        userService.insert(user);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody User user) {
        userService.updateById(user);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable long id){
        userService.deleteById(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam Integer pageNum,
                              @RequestParam Integer pageSize,
                              @RequestParam String search){
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        //避免search为null时查出数据为null
        wrapper.like(StrUtil.isNotBlank(search),User::getNickName,search);

        Page<User> userPage = userService.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(userPage);
    }

}
