package com.between.techchallenge.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiError {
    private String type = "/error/";
    private String title = "Unknown error";
    private String status = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
    private int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
    private String detail = "Unknown error";

    public ApiError() {
    }

    public ApiError(ValidationError knownError, String detailMessage) {
        this.type = knownError.type;
        this.title = knownError.title;
        this.status = knownError.status.getReasonPhrase();
        this.code = knownError.status.value();
        this.detail = detailMessage;
    }

    public ApiError buildInternalServerError() {
        this.type = "/error/internalServerError";
        this.title = "Unknown Internal Server error.";
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.detail = "Unknown Internal Server error.";
        return this;
    }

    public enum ValidationError {
        REQUIRED_PARAM("/error/invalid_param/required", "Required param is missing.", HttpStatus.BAD_REQUEST),
        TYPE_PARAM("/error/invalid_param/type", "Invalid type.", HttpStatus.BAD_REQUEST);

        private final String type;
        private final String title;
        private final HttpStatus status;

        ValidationError(String type, String title, HttpStatus status) {
            this.type = type;
            this.title = title;
            this.status = status;
        }
    }
}