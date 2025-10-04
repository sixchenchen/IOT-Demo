package com.moyan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.moyan.exception.AccountLockedException;
import com.moyan.exception.LoginFailedException;
import com.moyan.mapper.MenuMapper;
import com.moyan.pojo.LoginUser;
import com.moyan.pojo.SysUser;
import com.moyan.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.moyan.constant.MessageConstant.LOGIN_FAILED_USERNAME_OR_PASSWORD_ERROR;

/**
 * @program: IOT-Demo
 * @ClassName UserDetailsServiceImpl
 * @description:
 * @author: chen
 * @create: 2025-09-14 11:26
 **/


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    MenuMapper menuMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户信息
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserName, username);
        SysUser user = userMapper.selectOne(wrapper);
        //用户或密码错误,用户被标记删除异常
        if (Objects.isNull(user) || user.getDelFlag() == 1) {
            throw new LoginFailedException(LOGIN_FAILED_USERNAME_OR_PASSWORD_ERROR);
        }
        //查询权限信息封装到LoginUser中
        List<String> list = menuMapper.selectPermsByUserId(user.getId());
        // 将用户信息封装到UserDetails实现类中
        return new LoginUser(user,list);
    }
}