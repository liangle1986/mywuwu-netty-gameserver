package com.mywuwu.gameserver.mapper.service.Impl;

import com.mywuwu.gameserver.mapper.entity.User;
import com.mywuwu.gameserver.mapper.mapper.UserMapper;
import com.mywuwu.gameserver.mapper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 梁乐乐
 * @Date: 2019/2/15 09:59
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getTest(){
        return userMapper.selectAll();
    }
}
