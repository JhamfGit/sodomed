package com.sodemed.dtos.users.response;

public class DtoForgotPassword {

    public String name;
    public String email;

    public DtoForgotPassword(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
