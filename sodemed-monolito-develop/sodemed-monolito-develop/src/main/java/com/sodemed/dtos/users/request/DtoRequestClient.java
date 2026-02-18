package com.sodemed.dtos.users.request;


import com.sodemed.models.users.enums.TypeClient;


public class DtoRequestClient extends DtoRequestUser {
    
    private String address;
    private TypeClient type = TypeClient.contizing;


    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public TypeClient getType() {
        return type;
    }
    public void setType(TypeClient type) {
        this.type = type;
    }

}
