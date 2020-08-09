package com.restconsumer.configurations;


import com.restconsumer.helpers.RequestHelper;
import com.restconsumer.models.AuthResponse;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.net.URISyntaxException;

@org.springframework.context.annotation.Configuration
@Log
@EnableScheduling
public class Configuration extends WebMvcConfigurerAdapter {

    @Bean
    public AuthResponse getToken() throws URISyntaxException {
        log.info("getToken");
        return RequestHelper.getAuth();
    }
}
