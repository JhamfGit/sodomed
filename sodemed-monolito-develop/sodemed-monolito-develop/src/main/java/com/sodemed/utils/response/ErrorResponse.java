package com.sodemed.utils.response;

public class ErrorResponse {
    private String title;
    private String message;
    private String code;

    public ErrorResponse(String message, String title) {
        this.title = title;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return this.code = this.message.replaceAll(":", "_").replaceAll("\\s+", "_").toLowerCase();
    }

}
