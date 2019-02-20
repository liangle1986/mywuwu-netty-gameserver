package com.mywuwu.gameserver.mapper.service.Impl;

import com.mywuwu.gameserver.mapper.entity.Test;
import com.mywuwu.gameserver.mapper.mapper.TestMapper;
import com.mywuwu.gameserver.mapper.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 梁乐乐
 * @Date: 2019/2/15 09:59
 * @Description:
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;

    @Override
    public List<Test> getTest(){
        return testMapper.selectAll();
    }
}
