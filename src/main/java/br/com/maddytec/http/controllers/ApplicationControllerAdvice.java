package br.com.maddytec.http.controllers;

import br.com.maddytec.exception.NegocioException;
import br.com.maddytec.exception.PedidoNaoEncontradoException;
import br.com.maddytec.http.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(NegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNogocioException(NegocioException negocioException){
        return new ErrorResponse(negocioException.getMessage());
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public  ErrorResponse handPedidoNaoEncontradoException(PedidoNaoEncontradoException pedidoNaoEncontradoException){
        return new ErrorResponse(pedidoNaoEncontradoException.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        List<String> erros = methodArgumentNotValidException.getBindingResult().getAllErrors().stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());
        return new ErrorResponse(erros);
    }
}
