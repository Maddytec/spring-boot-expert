package br.com.maddytec.http.controllers;

import br.com.maddytec.exception.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(NegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMsg handleNogocioException(NegocioException negocioException){
        return new ErrorMsg(negocioException.getMessage());
    }
}
