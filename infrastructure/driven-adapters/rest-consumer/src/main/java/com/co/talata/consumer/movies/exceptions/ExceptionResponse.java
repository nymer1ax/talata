package com.co.talata.consumer.movies.exceptions;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class ExceptionResponse {
    private String message;
    private String code;
    private LocalDateTime date;

}
