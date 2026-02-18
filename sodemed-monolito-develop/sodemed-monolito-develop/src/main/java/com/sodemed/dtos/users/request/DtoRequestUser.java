package com.sodemed.dtos.users.request;

import com.sodemed.models.users.enums.StatusUser;
import com.sodemed.models.users.enums.TypeIdentification;
import com.sodemed.models.users.enums.UserType;


public class DtoRequestUser {
    
    private TypeIdentification typeIdentification;
    private String identification;
    private String name;
    private String lastName;
    private String phone;
    private String countryDialCode;
    private String email;
    private String password;
    private StatusUser status;
    private UserType userType;


    public TypeIdentification getTypeIdentification() {
        return typeIdentification;
    }
    public void setTypeIdentification(TypeIdentification typeIdentification) {
        this.typeIdentification = typeIdentification;
    }
    public String getIdentification() {
        return identification;
    }
    public void setIdentification(String identification) {
        this.identification = identification;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getCountryDialCode() {
        return countryDialCode;
    }
    public void setCountryDialCode(String countryDialCode) {
        this.countryDialCode = countryDialCode;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public StatusUser getStatus() {
        return status;
    }
    public void setStatus(StatusUser status) {
        this.status = status;
    }
    public UserType getUserType() {
        return userType;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    
}
