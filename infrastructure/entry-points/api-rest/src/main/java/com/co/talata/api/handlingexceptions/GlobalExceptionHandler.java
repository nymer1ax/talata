package com.co.talata.api.handlingexceptions;

import com.co.talata.usecase.exceptions.Response;
import com.co.talata.usecase.exceptions.custom.CustomException;
import com.co.talata.usecase.exceptions.custom.MovieNotFoundException;
import com.co.talata.usecase.exceptions.custom.UnauthorizedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.io.IOException;
import java.time.LocalDateTime;

import java.util.Collections;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {MovieNotFoundException.class})
    public ResponseEntity<Response> movieNotFound(MovieNotFoundException ex){

        Response response = Response.builder()
                .fecha(LocalDateTime.now().toString())
                .codigoResultado("NOT_FOUND")
                .descripcionRespuesta(ex.getMessage())
                .result(Collections.emptyList())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UnauthorizedException.class})
    public ResponseEntity<Response> unauthorized(UnauthorizedException ex){

        Response response = Response.builder()
                .fecha(LocalDateTime.now().toString())
                .codigoResultado("UNAUTHORIZED")
                .descripcionRespuesta(ex.getMessage())
                .result(Collections.emptyList())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<Response> custom(CustomException ex){
        Response response = Response.builder()
                .fecha(LocalDateTime.now().toString())
                .codigoResultado("BAD_REQUEST")
                .descripcionRespuesta(ex.getMessage())
                .result(Collections.emptyList())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Response> custom(RuntimeException ex){
        Response response = Response.builder()
                .fecha(LocalDateTime.now().toString())
                .codigoResultado("BAD_REQUEST")
                .descripcionRespuesta(ex.getMessage())
                .result(Collections.emptyList())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @ExceptionHandler(value = {IOException.class})
    public ResponseEntity<Response> custom(IOException ex){

        Response response = Response.builder()
                .fecha(LocalDateTime.now().toString())
                .codigoResultado("BAD_GATEWAY")
                .descripcionRespuesta(ex.getMessage())
                .result(Collections.emptyList())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
    }








}
