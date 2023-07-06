package br.tec.rental.library.api.apireponsesmodel;

import lombok.Getter;

@Getter
public class ResponseBody {
    private String message;

    public ResponseBody(String message) {
        this.message = message;
    }
}
