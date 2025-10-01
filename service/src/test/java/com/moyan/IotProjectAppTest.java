package com.moyan;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moyan.pojo.SysUser;
import com.moyan.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple IotProjectApp.
 */
@SpringBootTest(classes = IotProjectApp.class)
public class IotProjectAppTest{

    @Autowired
    private SysUserMapper sysUserMapper;
    @Test
    public void GetUser(){
        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserName, "admin123");
        SysUser user = sysUserMapper.selectOne(wrapper);

        System.out.println(user);
    }
}
