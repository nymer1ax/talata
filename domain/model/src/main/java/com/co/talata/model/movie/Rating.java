package com.co.talata.model.movie;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Rating {
    private boolean success;
    private float status_code;
    private String status_message;
}
