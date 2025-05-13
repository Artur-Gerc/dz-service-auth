package ru.dzserviceauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.dzserviceauth.handlers.UserArgumentResolver;

import java.util.List;

@SpringBootApplication
@Configuration
public class DzServiceAuthApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(DzServiceAuthApplication.class, args);
    }


    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new UserArgumentResolver());
    }
}
