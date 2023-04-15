package com.tacs.config;

import com.tacs.dto.ApiExceptionDto;
import com.tacs.exception.ApiException;
import io.javalin.http.Context;

public class ExceptionHandler {
  public Context handleApiException(ApiException apiException, Context context) {
    return context
        .status(apiException.getStatusCode())
        .json(new ApiExceptionDto(apiException.getMessage(), apiException.getStatusCode()));
  }
}
