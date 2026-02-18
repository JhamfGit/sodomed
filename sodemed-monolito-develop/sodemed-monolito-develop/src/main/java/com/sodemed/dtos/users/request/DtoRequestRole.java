package com.sodemed.dtos.users.request;

import java.util.List;

import com.sodemed.models.users.enums.StatusRole;

public class DtoRequestRole {

    private String name;
    private String code;
    private String description;
    private StatusRole status;
    private List<String> permissions;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public StatusRole getStatus() {
        return status;
    }
    public void setStatus(StatusRole status) {
        this.status = status;
    }
    public List<String> getPermissions() {
        return permissions;
    }
    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
    
}
