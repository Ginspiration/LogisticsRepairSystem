package com.ginspiration.serverbackground.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Authof: ZhangYingHao
 * @Date: Create in 16:32 2018/8/8
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${save.file.path}")
    private String saveFilePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("file:"+saveFilePath+"/");
    }
}