package com.lms.forget_password.exceptions;

import com.lms.forget_password.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions extends Exception{

    @Autowired
    private Response response;

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<Response> globalExceptionHandler (UserException userException){

        response.setMessage(userException.getMessage());
        return new ResponseEntity<>(response,userException.getStatus());

    }
}