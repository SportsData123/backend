package com.sports.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/api/**")
                .allowedOrigins(
                        "http://localhost:5173",
                        "https://national-sports-data.vercel.app/",
                        "https://www.national-sports-data.site")
                .allowedMethods("GET", "POST","PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
