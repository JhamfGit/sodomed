package com.sodemed.dtos.users;

import com.sodemed.models.users.enums.TypeIdentification;

public class LoginUser {
    private String login;
    private String password;
    private TypeIdentification typeIdentification;

    public LoginUser() {
    }

    public LoginUser(String login, String password, TypeIdentification typeIdentification) {
        this.login = login;
        this.password = password;
        this.typeIdentification = typeIdentification;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public TypeIdentification getTypeIdentification() {
        return typeIdentification;
    }

    public void setTypeIdentification(TypeIdentification typeIdentification) {
        this.typeIdentification = typeIdentification;
    }

}
