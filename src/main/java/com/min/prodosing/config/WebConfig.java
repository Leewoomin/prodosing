package com.min.prodosing.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${upload.summer}")
    private String uploadsummer;

    @Value("${upload.path}")
    private String uploadteamprofile;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/summernoteImg/**")
                .addResourceLocations("file:///" + uploadsummer);

        registry.addResourceHandler("/images/artistTeamProfile/**")
                .addResourceLocations("file:///" + uploadteamprofile);
    }

}
