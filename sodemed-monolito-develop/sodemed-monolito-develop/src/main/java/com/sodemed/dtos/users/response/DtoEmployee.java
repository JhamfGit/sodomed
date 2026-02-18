package com.sodemed.dtos.users.response;

public class DtoEmployee extends DtoUser{


    private DtoRole role;

    public DtoRole getRole() {
        return role;
    }

    public void setRole(DtoRole role) {
        this.role = role;
    }

}
