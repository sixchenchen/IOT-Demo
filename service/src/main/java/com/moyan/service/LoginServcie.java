package com.moyan.service;

import com.moyan.pojo.dto.LoginUserDTO;

import java.util.HashMap;

/**
 * @program: IOT-Demo
 * @ClassName LoginServcie
 * @description:
 * @author: chen
 * @create: 2025-09-14 11:49
 **/

public interface LoginServcie {
    HashMap<String, Object> login(LoginUserDTO loginUserDTO);

    void logout();
}