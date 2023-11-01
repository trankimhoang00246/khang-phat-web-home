package com.khangphat.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericResponse {
    private String message;
    private Object content;

    public GenericResponse(String message) {
        this.message = message;
    }

}
