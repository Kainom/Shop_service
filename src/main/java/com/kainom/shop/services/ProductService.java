package com.kainom.shop.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.kainom.dtos.ProductDTO;


@Service
public class ProductService {

    private final WebClient webClient;

    public ProductService(@Qualifier("product") WebClient webClient) {
        this.webClient = webClient;
    }

    public ProductDTO getProductByIdentifier(String productIdentifier){
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/{productIdentifier}").build(productIdentifier))
        .retrieve()
        .bodyToMono(ProductDTO.class)
        .block();
    }

}
