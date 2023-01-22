package com.dasun.stockservice.constants;

public class ErrorCodes {
    // Custom status codes
    public static final int INVALID_INPUT_ERROR_CODE = 1200;
    public static final int MISSING_API_FIELD_ERROR_CODE = 1201;
    public static final int API_OBJECT_FIELD_VALIDATION_ERROR_CODE = 1202;
    public static final int INVALID_TOKEN_ERROR_CODE = 1400;
    public static final int PERMISSION_DENIDED_ERROR_CODE = 1500;
    public static final int INTERNAL_ERROR_CODE = 1303;
    public static final String INVALID_INPUT_ERROR_MSG = "Invalid Input";
    public static final String INVALID_TOKEN_ERROR_MSG = "Invalid token";
    public static final String INTERNAL_ERROR_MSG = "Internal error occured";
    public static final String PERMISSION_DENIDED_ERROR_MSG = "Permission denied";
    public static final String MISSING_API_FIELD_ERROR_MSG = "Missing API field";
    public static final String API_OBJECT_FIELD_VALIDATION_ERROR_MSG = "API Object field validation error";
}
