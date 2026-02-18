package com.sodemed.dtos.users.request;


public class DtoRequestEmployee extends DtoRequestUser{


    private DtoRequestRole role;
    private long roleId;

    public DtoRequestRole getRole() {
        return role;
    }

    public void setRole(DtoRequestRole role) {
        this.role = role;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

}
