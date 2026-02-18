package com.sodemed.utils.response;

import org.springframework.http.HttpStatus;


public class ResponseData<T> {
    private String message;
    private T data;
    private int code;
    private boolean success;
    private String typeResponse; 
    private ErrorResponse error;

    public ResponseData() {
    }

    public ResponseData(HttpStatus httpStatus) {
        this.message = httpStatus.getReasonPhrase();
        this.data = null;
        this.code = httpStatus.value();
        this.success = !httpStatus.isError();
        this.typeResponse = httpStatus.series().toString();
        this.error = null;
    }

    public ResponseData(HttpStatus httpStatus, T data) {
        this.message = httpStatus.getReasonPhrase();
        this.data = data;
        this.code = httpStatus.value();
        this.success = !httpStatus.isError();
        this.typeResponse = httpStatus.series().toString();
        this.error = null;
    }

    public ResponseData(int code, ErrorResponse error, String message, boolean success) {
        this.message = message;
        this.data = null;
        this.code = code;
        this.success = success;
        this.typeResponse = getType(code);
        this.error = error;
    }
    
    public ResponseData(HttpStatus httpStatus, ErrorResponse error) {
        this.message = httpStatus.getReasonPhrase();
        this.data = null;
        this.code = httpStatus.value();
        this.success = !httpStatus.isError();
        this.typeResponse = httpStatus.series().toString();
        this.error = error;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSucces(boolean success) {
        this.success = success;
    }

    public String getTypeResponse() {
        return typeResponse;
    }

    public void setTypeResponse(String typeResponse) {
        this.typeResponse = typeResponse;
    }

    
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }

    private String getType(int code) {
            switch (code / 100) {
            case 1:
                return "INFORMATIONAL";
            case 2:
                return "SUCCESSFUL";
            case 3:
                return "REDIRECTION";
            case 4:
                return "CLIENT_ERROR";
            case 5:
                return "SERVER_ERROR";
            default:
                return "OTHER";
            }
    }
    

}


