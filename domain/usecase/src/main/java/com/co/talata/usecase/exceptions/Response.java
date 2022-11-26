package com.co.talata.usecase.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Response {
    private String codigoResultado;
    private String descripcionRespuesta;
    private LocalDateTime fecha;
    public Object result;
}
