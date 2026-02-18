package com.sodemed.models.users;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.List;

import com.sodemed.models.medicationorders.MedicationOrder;
import com.sodemed.models.users.enums.StatusUser;
import com.sodemed.models.users.enums.TypeClient;
import com.sodemed.models.users.enums.TypeIdentification;

@Entity
public class Client extends User {

    private String address;
    @Enumerated(EnumType.STRING)
    private TypeClient type = TypeClient.contizing;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contizing")
    private List<Beneficiary> beneficiaries;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userCreate")
    private List<MedicationOrder> orders;

    public Client(TypeIdentification typeIdentification, String identification, String name, String lastName,
            String phone, String countryDialCode, String email, String password, StatusUser status, String address,
            TypeClient type) {
        super(typeIdentification, identification, name, lastName, phone, countryDialCode, email, password, status);
        this.address = address;
        this.type = type;
    }

    public Client() {
    }

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

    public List<Beneficiary> getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(List<Beneficiary> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

    public List<MedicationOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<MedicationOrder> orders) {
        this.orders = orders;
    }

}
