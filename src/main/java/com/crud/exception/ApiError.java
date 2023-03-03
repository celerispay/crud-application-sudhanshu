package com.crud.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiError {

	private String errorMessage;

    private String errorCode;

    private String request;

    private String requestType;

    private String customMessage;
}
