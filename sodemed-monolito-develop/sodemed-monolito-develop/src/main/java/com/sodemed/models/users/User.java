package com.sodemed.models.users;

import jakarta.persistence.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sodemed.models.users.enums.StatusUser;
import com.sodemed.models.users.enums.TypeIdentification;
import com.sodemed.models.users.enums.UserType;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

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
    private String password;
    @Enumerated(EnumType.STRING)
    private StatusUser status;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private String login;

    public User() {
        super();
    }

    public User(long id) {
        super();
        this.id = id;
    }

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User(TypeIdentification typeIdentification, String identification, String name, String lastName,
            String phone, String countryDialCode, String email, String password, StatusUser status) {
        this.typeIdentification = typeIdentification;
        this.identification = identification;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.countryDialCode = countryDialCode;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public User(String password, String email) {
        this.name = "This data is pending for update";
        this.password = password;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StatusUser getStatus() {
        return status;
    }

    public void setStatus(StatusUser status) {
        this.status = status;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean validEmail() {
        if (this.email != null) {
            Matcher matcher = pattern.matcher(this.email);
            return matcher.find();
        }
        return false;
    }
}
