package ru.zvo.walkingroutesgh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login").setViewName("login");

        //краевед
//        registry.addViewController("/historic-main").setViewName("historic-main-page");
//        registry.addViewController("/edit-sights").setViewName("edit-sights");
//        registry.addViewController("/my-edits").setViewName("my-edits");

        //администратор
//        registry.addViewController("/admin-main").setViewName("admin-main-page");
//        registry.addViewController("/add-user").setViewName("add-user");

        //модератор
//        registry.addViewController("/moderator-main").setViewName("moderator-main-page");
//        registry.addViewController("/users").setViewName("users");
//        registry.addViewController("/historian-edits").setViewName("historian-edits");
    }

}
