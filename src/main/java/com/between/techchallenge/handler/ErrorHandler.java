package com.between.techchallenge.handler;

import com.between.techchallenge.error.ApiError;
import com.between.techchallenge.error.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@ControllerAdvice
public class ErrorHandler {
    Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ApiError handleThrowable(Throwable ex) {
        logger.error("Unknown Internal Server error. Message: " + ex.getMessage() + " - Cause: " + ex.getCause(), ex);
        return new ApiError().buildInternalServerError();
    }

    @ExceptionHandler(CustomException.class)
    public ApiError handleCustomException(CustomException ex, HttpServletResponse response) {
        logger.debug("Custom exception.", ex);
        if (ex.getThrowable() != null) {
            Throwable throwable = ex.getThrowable();
            logger.error("Unknown error. Message: " + throwable.getMessage() + " - Cause: " + throwable.getCause(), throwable);
        }
        response.setStatus(ex.getApiError().getCode());
        return ex.getApiError();
    }
}
