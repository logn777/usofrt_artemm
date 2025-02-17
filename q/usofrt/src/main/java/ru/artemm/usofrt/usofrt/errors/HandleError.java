package ru.artemm.usofrt.usofrt.errors;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class HandleError {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(exception = IllegalArgumentException.class)
    public String IllegalArgumentExceptionHandler() {
        return "Произошла ошибка";
    }
}
