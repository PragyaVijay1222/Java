package com.arcana.AttendanceService.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignClientConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                            .getRequest().getHeader("Authorization");
            if (token != null) {
                requestTemplate.header("Authorization", token);
            }
        };
    }
}
