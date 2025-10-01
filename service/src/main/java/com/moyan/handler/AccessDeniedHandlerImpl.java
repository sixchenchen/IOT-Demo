package com.moyan.handler;

import com.alibaba.fastjson.JSON;
import com.moyan.result.ResponseResult;
import com.moyan.utils.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * @program: IOT-Demo
 * @ClassName AccessDeniedHandlerImpl
 * @description:
 * @author: chen
 * @create: 2025-09-14 12:22
 **/

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) {
        ResponseResult<Object> result = ResponseResult.error(HttpStatus.FORBIDDEN.value(),
                "权限不足");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}