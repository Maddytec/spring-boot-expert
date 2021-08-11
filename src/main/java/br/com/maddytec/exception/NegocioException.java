package br.com.maddytec.exception;

public class NegocioException extends RuntimeException{

    public NegocioException(String mensagem){
        super(mensagem);
    }
}
