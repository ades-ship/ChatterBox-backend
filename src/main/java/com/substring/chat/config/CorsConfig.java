package com.substring.chat.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Apply CORS to all endpoints
                        .allowedOrigins("http://localhost:3000")  // Allow frontend
                       
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // HTTP methods
                        .allowedHeaders("*")  // Allow all headers
                        .allowCredentials(true);
                        
            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                // Map the /uploads/** URL to the uploads directory
                registry.addResourceHandler("/uploads/**")
                        .addResourceLocations("file:uploads/");
            }
        };
    }
}
