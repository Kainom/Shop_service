package com.kainom.shop.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;

import com.kainom.dtos.UserDTO;
import com.kainom.err.UserNotFoundException;

@Service
public class UserService {

    private final WebClient wClient;

    public UserService(@Qualifier("user") WebClient wClient) {
        this.wClient = wClient;
    }

    public UserDTO getUserByCpf(String cpf) {
       try{
 // return wClient.get().uri("cpf" +
        // cpf).retrieve().bodyToMono(UserDTO.class).block();
        return wClient.get().uri(uriBuilder -> uriBuilder.path("{/cpf}").build(cpf))
                .retrieve()
                .bodyToMono(UserDTO.class) // Converte a resposta para UserDTO
                .block(); // Bloqueia para obter o resultado de forma síncrona

       }catch(HttpClientErrorException.NotFound notFound){
            throw new UserNotFoundException();
       }
    }

}
