package com.example.monki.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin/login").setViewName("login");
        registry.addViewController("/admin/logout").setViewName("logout");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/reserve/success").setViewName("success");
        registry.addViewController("/order/complete").setViewName("complete");
    }
}
