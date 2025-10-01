package com.moyan.filter;

import com.moyan.constant.TokenConstant;
import com.moyan.context.BaseContext;
import com.moyan.pojo.LoginUser;
import com.moyan.utils.JwtUtil;
import com.moyan.utils.RedisCache;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @program: IOT-Demo
 * @ClassName JwtAuthenticationTokenFilter
 * @description:
 * @author: chen
 * @create: 2025-09-14 11:56
 **/

//OncePerRequestFilter特点是在处理单个HTTP请求时确保过滤器的 doFilterInternal 方法只被调用一次
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final List<String> EXCLUDE_PATHS = Arrays.asList(
            "/login",
            "/register",
            "/swagger-ui",
            "/v3/api-docs",
            "/doc.html"
    );

    @Autowired
    RedisCache redisCache;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return EXCLUDE_PATHS.stream().anyMatch(path::startsWith);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            //1.在请求头中获取token
            String token = request.getHeader(TokenConstant.TOKEN_HEADER);
            // 支持 Bearer token 格式
            if (StringUtils.hasText(token) && token.startsWith(TokenConstant.TOKEN_PREFIX)) {
                token = token.substring(7);
            }

            //此处需要判断token是否为空
            if (!StringUtils.hasText(token)){
                //没有token放行 此时的SecurityContextHolder没有用户信息 会被后面的过滤器拦截
                filterChain.doFilter(request,response);
                return;
            }

            //2.解析token获取用户id
            String subject;
            try {
                Claims claims = JwtUtil.parseJWT(token);
                subject = claims.getSubject();

                // 检查token是否过期
                Date expiration = claims.getExpiration();
                if (expiration != null && expiration.before(new Date())) {
                    throw new RuntimeException("token已过期");
                }
            } catch (Exception e) {
                //解析失败
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("token非法或已过期");
                return;
            }

            //3.在redis中获取用户信息
            String redisKey = "login:" + subject;
            LoginUser loginUser = redisCache.getCacheObject(redisKey);

            //此处需要判断loginUser是否为空
            if (Objects.isNull(loginUser)){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("用户未登录或登录已过期");
                return;
            }

            //4.将获取到的用户信息存入SecurityContextHolder
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            //5.将用户id存入ThreadLocal
            BaseContext.setCurrentId(loginUser.getUser().getId());
            //6.放行
            filterChain.doFilter(request,response);
        } finally {
            // 清理线程局部变量，防止内存泄漏
            BaseContext.removeCurrentId();
        }
    }
}