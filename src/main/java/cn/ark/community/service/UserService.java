package cn.ark.community.service;

import cn.ark.community.dao.UserMapper;
import cn.ark.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: community
 * @description:
 * @author: Ark
 * @create: 2022-02-16 15:55
 **/
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User findUserById(int id){
        return userMapper.selectById(id);
    }

    public User findUerByEmail(String email){
        return userMapper.selectByEmail(email);
    }
    public User findUserByName(String name){
        return userMapper.selectByName(name);
    }

}
