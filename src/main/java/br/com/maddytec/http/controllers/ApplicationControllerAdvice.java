package br.com.maddytec.http.controllers;

import br.com.maddytec.exception.NegocioException;
import br.com.maddytec.exception.PedidoNaoEncontradoException;
import br.com.maddytec.http.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(NegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNogocioException(NegocioException negocioException){
        return new ErrorResponse(negocioException.getMessage());
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public  ErrorResponse handPedidoNaoEncontradoException(PedidoNaoEncontradoException ex){
        return new ErrorResponse(ex.getMessage());
    }
}
