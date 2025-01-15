package com.kainom.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${API_USER:http://localhost:8000/user}")
    private String userApiURL;

    @Value("${API_PRODUCT:http://localhost:8001/product}")
    private String productApiURL;

    @Bean(name = "user")
    public WebClient webClientUser(WebClient.Builder builder) {
        return builder.baseUrl(userApiURL).build();
    }

    @Bean(name = "product")
    public WebClient webClientProduct(WebClient.Builder builder) {
        return builder.baseUrl(productApiURL).build();
    }

}
