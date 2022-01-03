package io.vasilizas.exception;

public class MyWebAppException extends RuntimeException {
    public MyWebAppException(String message) {
        super(message);
    }

    public MyWebAppException() {
    }
}
