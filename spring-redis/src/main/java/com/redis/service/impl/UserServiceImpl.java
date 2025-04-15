package com.redis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.redis.bean.User;
import com.redis.mapper.UserMapper;
import com.redis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-15  9:24
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class) ;
    public static final String CACHE_KEY_USER= "user:";

    RedisTemplate redisTemplate;
    UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }


    @Autowired
    public void setUserMapper(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }
    @Override
    public void createUser(User user) {
        this.userMapper.insert(user);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername,user.getUsername());
        User user1 =userMapper.selectOne(queryWrapper);
        String key = CACHE_KEY_USER+user1.getId();
        redisTemplate.opsForValue().set(key,user1);
        System.out.println(redisTemplate.opsForValue().get(key));
    }

    @Override
    public void updateUser(User user) {
         this.userMapper.updateById(user);
         String key = CACHE_KEY_USER+user.getId();
         user = this.userMapper.selectById(user.getId());
         redisTemplate.opsForValue().set(key,user);
    }

    @Override
    public User findUserById(Integer id) {
        ValueOperations<String,User> operations = redisTemplate.opsForValue();
        String key = CACHE_KEY_USER+id;
        User user = (User) operations.get(key);
        if(null==user){
            System.out.println("查询了数据库");
            user = this.userMapper.selectById(id);
            operations.set(key,user);
        }
        return user;
    }
}
