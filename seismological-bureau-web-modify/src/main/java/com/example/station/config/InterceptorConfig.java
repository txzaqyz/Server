package com.example.station.config;


import com.example.station.interceptors.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
//                拦截路径
                .addPathPatterns("/**")
//                放行路径
                .excludePathPatterns("/login")
                .excludePathPatterns("/register");

    }
}
