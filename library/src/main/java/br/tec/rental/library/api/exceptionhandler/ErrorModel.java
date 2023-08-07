package br.tec.rental.library.api.exceptionhandler;

import lombok.Getter;

@Getter
public class ErrorModel {
    private String message;
    public ErrorModel(String message) {
        this.message = message;
    }
}
