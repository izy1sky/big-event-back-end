package org.izy1sky.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.izy1sky.springboot.interceptor.JWTTokenInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private JWTTokenInterceptor jwtTokenInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtTokenInterceptor).excludePathPatterns("/user/login", "/user/register");
    }
}
