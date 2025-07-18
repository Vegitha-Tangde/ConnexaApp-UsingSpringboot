package com.example.Connexa.Connexa.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    private String message;
    private String details;
    private LocalDateTime timestamp;
}
