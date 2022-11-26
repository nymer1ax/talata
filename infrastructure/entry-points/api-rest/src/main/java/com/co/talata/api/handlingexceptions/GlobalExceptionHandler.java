package com.co.talata.api.handlingexceptions;

import com.co.talata.usecase.exceptions.Response;
import com.co.talata.usecase.exceptions.custom.MovieNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<Response> movieNotFound(MovieNotFoundException ex){

        Response response = Response.builder()
                .fecha(LocalDateTime.now())
                .codigoResultado("NOT_FOUND")
                .descripcionRespuesta(ex.getMessage())
                .result(Collections.emptyList())
                .build();

        return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
    }

}
