package com.moyan.controller.login;

import com.moyan.pojo.dto.LoginUserDTO;
import com.moyan.result.ResponseResult;
import com.moyan.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @program: IOT-Demo
 * @ClassName LoginController
 * @description:
 * @author: chen
 * @create: 2025-09-14 11:48
 **/

@RestController
@Tag(name = "登录管理")
public class LoginController {
    @Autowired
    private LoginService loginService;

    /**
     * 登录接口
     * @param loginUserDTO
     * @return
     */
    @Operation(summary = "登录接口")
    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginUserDTO loginUserDTO) {
        HashMap<String, Object> token = loginService.login(loginUserDTO);
        return ResponseResult.success("登录成功",token);
    }

    /**
     * 登出接口
     * @return
     */
    @PostMapping("/logout")
    @Operation(summary = "登出接口")
    public ResponseResult logout() {
        System.out.println("开始登出");
        loginService.logout();
        return ResponseResult.success("退出成功");
    }
}