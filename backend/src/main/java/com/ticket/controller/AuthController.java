package com.ticket.controller;

import com.ticket.dto.LoginDTO;
import com.ticket.entity.User;
import com.ticket.service.UserService;
import com.ticket.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginDTO dto) {
        User user = userService.findByUsername(dto.getUsername());
        
        if (user == null) {
            return Result.error("用户不存在");
        }
        
        if (!userService.checkPassword(dto.getPassword(), user.getPassword())) {
            return Result.error("密码错误");
        }
        
        // TODO: 生成JWT Token
        String token = "jwt-token-placeholder";
        return Result.success(token);
    }
    
    @PostMapping("/register")
    public Result<String> register(@RequestBody LoginDTO dto) {
        boolean success = userService.register(dto.getUsername(), dto.getPassword(), dto.getUsername());
        
        if (!success) {
            return Result.error("用户名已存在");
        }
        
        return Result.success("注册成功");
    }
}
