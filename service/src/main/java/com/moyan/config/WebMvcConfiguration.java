package com.moyan.config;

import com.moyan.json.JacksonObjectMapper;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@Slf4j
public class WebMvcConfiguration implements WebMvcConfigurer {

    /**
     * 用户管理端接口分组
     */
    @Bean
    public GroupedOpenApi userAdminApi() {
        return GroupedOpenApi.builder()
                .group("user-admin-api")
                .displayName("用户管理端")
                .packagesToScan("com.moyan.controller.user")  // 确保包含用户控制器包
                .build();
    }

    /**
     * 默认API分组（包含所有接口）
     */
    @Bean
    public GroupedOpenApi defaultApi() {
        return GroupedOpenApi.builder()
                .group("default")
                .packagesToScan("com.moyan.controller")  // 扫描所有控制器
                .build();
    }

    /**
     * 全局OpenAPI配置
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("项目名称 API 文档")
                        .description("基于 Spring Boot 3 和 Knife4j 的 RESTful API 文档")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("技术支持")
                                .email("support@example.com")
                                .url("https://www.example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                        .termsOfService("https://www.example.com/terms"));
    }

    /**
     * 设置静态资源映射 - 确保 Knife4j UI 可访问
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Knife4j 静态资源
        registry.addResourceHandler("/doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        // Swagger UI 静态资源（兼容性）
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springdoc-openapi-ui/");

        log.info("Knife4j 静态资源映射配置完成");
    }

    /**
     * 扩展消息转换器 - 使用自定义 Jackson 配置
     * 该方法用于配置和扩展 Spring MVC 的消息转换器，特别是针对 JSON 数据的处理
     * 通过自定义 Jackson 配置，可以实现更灵活的序列化和反序列化规则
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 使用流式处理遍历消息转换器列表
        converters.stream()
                // 筛选出类型为 MappingJackson2HttpMessageConverter 的转换器
                .filter(converter -> converter instanceof MappingJackson2HttpMessageConverter)
                // 获取第一个符合条件的转换器（如果存在）
                .findFirst()
                // 如果找到匹配的转换器，则执行自定义配置
                .ifPresent(converter -> {
                    // 将转换器强制类型转换为 MappingJackson2HttpMessageConverter
                    MappingJackson2HttpMessageConverter jacksonConverter =
                            (MappingJackson2HttpMessageConverter) converter;
                    // 设置自定义的 ObjectMapper 配置
                    jacksonConverter.setObjectMapper(new JacksonObjectMapper());
                    // 记录配置完成日志
                    log.info("自定义 Jackson 消息转换器配置完成");
                });
    }
}