package com.between.techchallenge.error;

public class CustomException extends Exception {

    private static final long serialVersionUID = 1L;

    private ApiError apiError;
    private Throwable throwable = null;

    public CustomException(ApiError apiError) {
        super();
        this.apiError = apiError;
    }

    public CustomException(ApiError apiError, Throwable ex) {
        super();
        this.apiError = apiError;
        this.throwable = ex;
    }

    public ApiError getApiError() {
        return apiError;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}