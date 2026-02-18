package com.sodemed.models.users;

import com.sodemed.models.users.enums.StatusUser;
import com.sodemed.models.users.enums.TypeIdentification;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
public class Employee extends User {

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    public Employee() {
    }

    public Employee(TypeIdentification typeIdentification, String identification, String name, String lastName,
            String phone, String countryDialCode, String email, String password, StatusUser status) {
        super(typeIdentification, identification, name, lastName, phone, countryDialCode, email, password, status);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
