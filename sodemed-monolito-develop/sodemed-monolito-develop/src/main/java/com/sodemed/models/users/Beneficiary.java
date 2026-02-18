package com.sodemed.models.users;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sodemed.models.users.enums.Kinship;
import com.sodemed.models.users.enums.StatusUser;
import com.sodemed.models.users.enums.TypeClient;
import com.sodemed.models.users.enums.TypeIdentification;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Beneficiary {
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private TypeIdentification typeIdentification;
    private String identification;
    private String name;
    private String lastName;
    private String phone;
    private String countryDialCode;
    private String email;
    @Enumerated(EnumType.STRING)
    private StatusUser status;
    @Enumerated(EnumType.STRING)
    private TypeClient userType = TypeClient.beneficiary;
    @Enumerated(EnumType.STRING)
    private Kinship kinship;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client contizing;

    public Beneficiary() {
        super();
    }

    public Beneficiary(long id) {
        super();
        this.id = id;
    }

    public Beneficiary(TypeIdentification typeIdentification, String identification, String name, String lastName,
            String phone, String countryDialCode, String email, String password, StatusUser status) {
        this.typeIdentification = typeIdentification;
        this.identification = identification;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.countryDialCode = countryDialCode;
        this.email = email;
        this.status = status;
    }

    public long getId() {
        return id;
    }

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

    public Client getContizing() {
        return contizing;
    }

    public void setContizing(Client contizing) {
        this.contizing = contizing;
    }

    public boolean validEmail() {
        if (this.email != null) {
            Matcher matcher = pattern.matcher(this.email);
            return matcher.find();
        }
        return false;
    }

    public void setId(long id) {
        this.id = id;
    }
}
