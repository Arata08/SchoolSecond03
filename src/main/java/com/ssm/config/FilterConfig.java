package com.ssm.config;

import com.ssm.filter.LoginCheckFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<LoginCheckFilter> loggingFilter(){
        FilterRegistrationBean<LoginCheckFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new LoginCheckFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1); // 设置过滤器顺序

        return registrationBean;
    }
}
