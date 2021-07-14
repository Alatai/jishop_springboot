package com.alatai.jishop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/14 11:12
 */
@Configuration
public class MvcConfiguration extends WebMvcConfigurationSupport {

    /**
     * 静态资源设置
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }

    /**
     * 跨域访问设置
     * 浏览器同源策略（Same-Origin Policy)
     * 默认情况下，AJAX请求只能发送给同源的URL
     * 同源：协议、域名、端口相同
     */
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        // 允许所有请求跨域访问
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
