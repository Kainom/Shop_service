package com.kainom.shop.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;

import com.kainom.dtos.ProductDTO;
import com.kainom.err.ProductNotFoundException;

@Service
public class ProductService {

    private final WebClient webClient;

    public ProductService(@Qualifier("product") WebClient webClient) {
        this.webClient = webClient;
    }

    public ProductDTO getProductByIdentifier(String productIdentifier) {
        try {
            return webClient.get().uri(uriBuilder -> uriBuilder.path("/{productIdentifier}").build(productIdentifier))
                    .retrieve()
                    .bodyToMono(ProductDTO.class)
                    .block();
        } catch (HttpClientErrorException.NotFound notFound) {
            throw new ProductNotFoundException();
        }
    }

}
