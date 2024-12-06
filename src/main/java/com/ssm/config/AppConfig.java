package com.ssm.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@MapperScan("com.ssm.dao")
public class AppConfig implements WebMvcConfigurer {

   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
       registry.addResourceHandler("/css/**").addResourceLocations("/css/");
       registry.addResourceHandler("/js/**").addResourceLocations("/js/");
       registry.addResourceHandler("/plugins/**").addResourceLocations("/plugins/");
       registry.addResourceHandler("/images/**").addResourceLocations("/images/");
       registry.addResourceHandler("/html/**").addResourceLocations("/html/");
       registry.addResourceHandler("/styles/**").addResourceLocations("/styles/");
   }

//       @Bean(name = "multipartResolver")
//       public CommonsMultipartResolver multipartResolver() {
//           CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//           resolver.setDefaultEncoding("UTF-8");
//           resolver.setMaxUploadSize(1024000);
//           return resolver;
//       }
}
   