package com.moyan.handler;

import com.alibaba.fastjson.JSON;
import com.moyan.constant.MessageConstant;
import com.moyan.result.ResponseResult;
import com.moyan.utils.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import static com.moyan.constant.MessageConstant.LOGIN_FAILED_USERNAME_OR_PASSWORD_ERROR;

/**
 * @program: IOT-Demo
 * @ClassName AuthenticationEntryPointImpl
 * @description:
 * @author: chen
 * @create: 2025-09-14 12:24
 **/

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse
            response, AuthenticationException authException) {
        ResponseResult<Object> result =
                ResponseResult.error(HttpStatus.UNAUTHORIZED.value(),LOGIN_FAILED_USERNAME_OR_PASSWORD_ERROR );
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}