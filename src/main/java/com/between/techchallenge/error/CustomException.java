package com.between.techchallenge.error;

public class CustomException extends Exception {

    private static final long serialVersionUID = 1L;

    private final ApiError apiError;

    public CustomException(ApiError apiError) {
        super();
        this.apiError = apiError;
    }

    public ApiError getApiError() {
        return apiError;
    }
}