package com.kainom.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {


    @Bean(name = "user")
    public WebClient  webClientUser(WebClient.Builder builder){
        return builder.baseUrl("http://localhost:8000/user").build();
    }

    @Bean(name="product")
    public WebClient webClientProduct(WebClient.Builder builder){
        return builder.baseUrl("http://localhost:8001/product").build();
    }

    
}
