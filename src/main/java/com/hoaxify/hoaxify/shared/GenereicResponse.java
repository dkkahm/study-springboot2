package com.hoaxify.hoaxify.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenereicResponse {
    private String message;

    public GenereicResponse(String message) {
        this.message = message;
    }
}
