package com.example.taskcreator.configs;

import com.example.taskcreator.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Autowired
    private JwtUtil jwtUtil;

    @Bean
    public FilterRegistrationBean<JwtTokenFilter> jwtTokenFilter() {
        FilterRegistrationBean<JwtTokenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtTokenFilter(jwtUtil));
//        registrationBean.addUrlPatterns("/dummyAPI/*");
        registrationBean.addUrlPatterns("/task/*");
        registrationBean.addUrlPatterns("/priority/*");
        registrationBean.addUrlPatterns("/status/*");
        return registrationBean;
    }
}
