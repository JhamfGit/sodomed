package com.sodemed.utils.response;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ResponseDataPageable<T> {

    private String message;
    private List<T> data;
    private int code;
    private boolean success;
    private String typeResponse;
    private ErrorResponse error;
    private int totalPages;
    private Long totalElements;
    private int size;
    private int number;

    public ResponseDataPageable() {
    }

    public ResponseDataPageable(HttpStatus httpStatus, Page<T> data) {
        this.message = httpStatus.getReasonPhrase();
        this.data = data.getContent();
        this.code = httpStatus.value();
        this.success = !httpStatus.isError();
        this.typeResponse = httpStatus.series().toString();
        this.error = null;
        this.totalPages = data.getTotalPages();
        this.totalElements = data.getTotalElements();
        this.size = data.getSize();
        this.number = data.getNumber();
    }

    public ResponseDataPageable(HttpStatus httpStatus, ErrorResponse error) {
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

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number + 1;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    
}
