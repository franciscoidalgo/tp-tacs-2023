package com.tacs.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiExceptionDto {
    String errorMessage;
    Integer statusCode;
}
