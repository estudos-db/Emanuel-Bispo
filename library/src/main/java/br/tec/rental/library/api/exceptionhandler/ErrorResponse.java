package br.tec.rental.library.api.exceptionhandler;

public class ErrorResponse {
    private ErrorModel error;

    public ErrorResponse(String message) {
        error = new ErrorModel(message);
    }

    public ErrorModel getError() {
        return error;
    }
}
