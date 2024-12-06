package com.ssm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"com.ssm.controller","com.ssm.config"})
@EnableWebMvc
public class SpringMvcConfig {
    //文件上传解析器
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        //设置上传文件编码
        resolver.setDefaultEncoding("UTF-8");
        //设置上传文件大小上限
        resolver.setMaxUploadSize(1024000);
        return resolver;
    }
}
