package br.com.maddytec.http.response;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ErrorResponse {

    @Getter
    private List<String> errors;

    public ErrorResponse(List<String> errors) {
        this.errors = errors;
    }

    public ErrorResponse (String mensageError){
        this.errors = Arrays.asList(mensageError);
    }
}
