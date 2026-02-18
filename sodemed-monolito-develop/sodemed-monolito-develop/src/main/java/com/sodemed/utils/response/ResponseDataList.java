package com.sodemed.utils.response;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ResponseDataList<T> {
    private String message;
    private List<T> data;
    private int code;
    private boolean success;
    private String typeResponse; 
    private ErrorResponse error;

     public ResponseDataList() {
    }

    public ResponseDataList(HttpStatus httpStatus, List<T> data) {
        this.message = httpStatus.getReasonPhrase();
        this.data = data;
        this.code = httpStatus.value();
        this.success = !httpStatus.isError();
        this.typeResponse = httpStatus.series().toString();
        this.error = null;
    }

    public ResponseDataList(HttpStatus httpStatus, ErrorResponse error) {
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
    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
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
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getTypeResponse() {
        return typeResponse;
    }
    public void setTypeResponse(String typeResponse) {
        this.typeResponse = typeResponse;
    }

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }
    
    
}
