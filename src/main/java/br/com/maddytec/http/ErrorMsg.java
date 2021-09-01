package br.com.maddytec.http;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ErrorMsg {

    @Getter
    private List<String> errors;

    public ErrorMsg (String mensageError){
        this.errors = Arrays.asList(mensageError);
    }
}
