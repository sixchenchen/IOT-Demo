package com.moyan.service.impl;

import com.moyan.constant.TokenConstant;
import com.moyan.pojo.LoginUser;
import com.moyan.pojo.dto.LoginUserDTO;
import com.moyan.service.LoginService;
import com.moyan.utils.JwtUtil;
import com.moyan.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @program: IOT-Demo
 * @ClassName LoginServiceImpl
 * @description:
 * @author: chen
 * @create: 2025-09-14 11:49
 **/

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RedisCache redisCache;

    /**
     * 登录方法,通过UserDetailsServiceImpl中的loadUserByUsername方法获取用户信息
     * @param loginUserDTO
     * @return
     */
    @Override
    public HashMap<String, Object> login(LoginUserDTO loginUserDTO) {

        //1.封装Authentication对象
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDTO.getUsername(), loginUserDTO.getPassword());

        //2.通过AuthenticationManager的authenticate方法来进行用户认证
        Authentication authenticated = authenticationManager.authenticate(authenticationToken);

        //3.在Authentication中获取用户信息
        LoginUser loginUser = (LoginUser) authenticated.getPrincipal();
        String userId = loginUser.getUser().getId().toString();

        //4.认证通过生成token
        String jwt = JwtUtil.createJWT(userId);
        //6.用户信息存入redis
        redisCache.setCacheObject(TokenConstant.TOKEN_INFO + userId, loginUser);
        //6.把token返回给前端
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(TokenConstant.TOKEN_HEADER, jwt);
        return hashMap;
    }

    /**
     * 退出登录
     */
    @Override
    public void logout() {
        //获取SecurityContextHolder中的用户id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        //删除redis中的用户信息
        redisCache.deleteObject(TokenConstant.TOKEN_INFO + userId);
    }
}