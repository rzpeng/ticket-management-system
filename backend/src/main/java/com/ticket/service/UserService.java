package com.ticket.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ticket.entity.User;
import com.ticket.mapper.UserMapper;
import org.springframework.stereotype.Service;
import cn.hutool.crypto.digest.BCrypt;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    
    public User findByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        return this.getOne(wrapper);
    }
    
    public boolean register(String username, String password, String realName) {
        if (findByUsername(username) != null) {
            return false;
        }
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(BCrypt.hashpw(password));
        user.setRealName(realName);
        user.setStatus(1);
        
        return this.save(user);
    }
    
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}
