package com.onandon.cafe.cafekioskapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

        private String connectPath = "/image/**";
        @Value("${spring.servlet.location}")
        private String FILE_PATH;

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler(connectPath)
                        .addResourceLocations("file:///"+FILE_PATH);
        }
}
