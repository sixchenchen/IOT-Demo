package com.moyan.utils;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @program: IOT-Demo
 * @ClassName WebUtils
 * @description:
 * @author: chen
 * @create: 2025-09-14 12:23
 **/

public class WebUtils {
    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String
            string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}