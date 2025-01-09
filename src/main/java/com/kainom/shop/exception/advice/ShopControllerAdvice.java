package com.kainom.shop.exception.advice;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.kainom.dtos.ErrorDTO;
import com.kainom.err.ProductNotFoundException;
import com.kainom.err.UserNotFoundException;

@ControllerAdvice(basePackages = "com.kainom.shop.controllers" /* Define o pacote principal que contém os controllers */)
public class ShopControllerAdvice {
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)

    public ErrorDTO handleProductNotFoundException(ProductNotFoundException exProduct) {
        String message = "Product not Found";
        Integer status = HttpStatus.NOT_FOUND.value();

        return new ErrorDTO(status, message, new Date());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorDTO handleUsuarioNotFoundException(UserNotFoundException exUser) {
        String message = "User not Found";
        Integer status = HttpStatus.NOT_FOUND.value();

        return new ErrorDTO(status, message, new Date());
    }

}
