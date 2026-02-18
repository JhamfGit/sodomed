package com.sodemed.dtos.users.request;

import com.sodemed.models.users.enums.Kinship;
import com.sodemed.models.users.enums.StatusUser;
import com.sodemed.models.users.enums.TypeClient;
import com.sodemed.models.users.enums.TypeIdentification;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class DtoRequestBeneficiary {

    private TypeIdentification typeIdentification;
    private String identification;
    private String name;
    private String lastName;
    private String phone;
    private String countryDialCode;
    @Column(unique = true)
    private String email;
    private StatusUser status;
    @Enumerated(EnumType.STRING)
    private TypeClient userType = TypeClient.beneficiary;
    @Enumerated(EnumType.STRING)
    private Kinship kinship;
    private long clientId;


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

    public StatusUser getStatus() {
        return status;
    }

    public void setStatus(StatusUser status) {
        this.status = status;
    }

    public TypeClient getUserType() {
        return userType;
    }

    public void setUserType(TypeClient userType) {
        this.userType = userType;
    }

    public Kinship getKinship() {
        return kinship;
    }

    public void setKinship(Kinship kinship) {
        this.kinship = kinship;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

}
