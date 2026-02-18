package com.sodemed.dtos.users.response;


import com.sodemed.models.users.enums.TypeClient;


public class DtoClient extends DtoUser {
    
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
