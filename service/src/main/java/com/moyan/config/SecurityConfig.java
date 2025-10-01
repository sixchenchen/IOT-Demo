package com.moyan.config;

import com.moyan.filter.JwtAuthenticationTokenFilter;
import com.moyan.handler.AccessDeniedHandlerImpl;
import com.moyan.handler.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @program: IOT-Demo
 * @ClassName SecurityConfig
 * @description:
 * @author: chen
 * @create: 2025-09-14 11:47
 **/

@Configuration //配置类
@EnableWebSecurity // 开启Spring Security的功能 代替了 implements WebSecurityConfigurerAdapter
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    AuthenticationConfiguration authenticationConfiguration;//获取AuthenticationManager
    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    AccessDeniedHandlerImpl accessDeniedHandler;
    @Autowired
    AuthenticationEntryPointImpl authenticationEntryPoint;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * 配置Spring Security的过滤链。
     *
     * @param http 用于构建安全配置的HttpSecurity对象。
     * @return 返回配置好的SecurityFilterChain对象。
     * @throws Exception 如果配置过程中发生错误，则抛出异常。
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 禁用CSRF保护
                .csrf(AbstractHttpConfigurer::disable)
                // 设置会话创建策略为无状态
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 配置授权规则指定/login路径.允许匿名访问. 其他路径需要身份认证
                .authorizeHttpRequests(auth -> auth
                        // 配置授权规则
                        .requestMatchers("/login").permitAll()// 登录允许匿名访问
                        .requestMatchers(
                                "/doc.html",                    // Knife4j 主页面
                                "/webjars/**",                  // webjars 静态资源
                                "/v3/api-docs/**",              // OpenAPI JSON 文档
                                "/swagger-ui/**",               // Swagger UI
                                "/swagger-ui.html",             // Swagger UI 页面
                                "/swagger-resources/**",        // Swagger 资源
                                "/configuration/ui",            // Swagger 配置
                                "/configuration/security",      // Swagger 安全配置
                                "/favicon.ico",                 // 网站图标
                                "/error"                        // 错误页面
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                // 禁用默认的logout处理，使用自定义的Controller
                .logout(AbstractHttpConfigurer::disable)
                //开启跨域访问
                .cors(AbstractHttpConfigurer::disable)
                // 添加JWT认证过滤器
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                // 配置异常处理
                .exceptionHandling(exception -> exception.
                        accessDeniedHandler(accessDeniedHandler).
                        authenticationEntryPoint(authenticationEntryPoint));
        //开启跨域访问
        http.cors(Customizer.withDefaults()); // 正确的跨域配置
        // 构建并返回安全过滤链
        return http.build();
    }
}