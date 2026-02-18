package com.sodemed.dtos.users.response;

import java.util.List;

import com.sodemed.models.users.User;
import com.sodemed.models.users.enums.TypeIdentification;
import com.sodemed.models.users.enums.UserType;

public class DtoSysUser {
    
    private String accesToken;
    private String name;
    private String lastName;
    private long userId;
    private UserType userType;
    private String identification;
    private TypeIdentification typeIdentification;
    private String phone;
    private String dialCode;
    private String email;
    private List<String> permissions;

    public DtoSysUser() {
    }

    public DtoSysUser(String accesToken, User user,List<String> permissions) {
        this.accesToken = accesToken;
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.userId = user.getId();
        this.userType = user.getUserType();
        this.identification = user.getIdentification();
        this.typeIdentification = user.getTypeIdentification();
        this.phone = user.getPhone();
        this.dialCode = user.getCountryDialCode();
        this.email = user.getEmail();
        this.permissions = permissions;
    }


    public String getAccesToken() {
        return accesToken;
    }
    public void setAccesToken(String accesToken) {
        this.accesToken = accesToken;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getidentification() {
        return identification;
    }

    public void setidentification(String identification) {
        this.identification = identification;
    }

    public TypeIdentification getTypeIdentification() {
        return typeIdentification;
    }

    public void setTypeIdentification(TypeIdentification typeIdentification) {
        this.typeIdentification = typeIdentification;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDialCode() {
        return dialCode;
    }

    public void setDialCode(String dialCode) {
        this.dialCode = dialCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    
    
    
}
