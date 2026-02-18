package com.sodemed.utils.users;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sodemed.dtos.users.request.DtoRequestBeneficiary;
import com.sodemed.dtos.users.request.DtoRequestClient;
import com.sodemed.dtos.users.request.DtoRequestEmployee;
import com.sodemed.dtos.users.request.DtoRequestRole;
import com.sodemed.dtos.users.response.DtoBeneficiary;
import com.sodemed.dtos.users.response.DtoClient;
import com.sodemed.dtos.users.response.DtoEmployee;
import com.sodemed.dtos.users.response.DtoRole;
import com.sodemed.dtos.users.response.DtoUser;
import com.sodemed.models.users.Beneficiary;
import com.sodemed.models.users.Client;
import com.sodemed.models.users.Employee;
import com.sodemed.models.users.Role;
import com.sodemed.models.users.User;
import com.sodemed.models.users.enums.TypeClient;
import com.sodemed.models.users.enums.UserType;

@Component
public class EntityMapper {

    private static String prefix;

    @Value("${prefix.login.admin}")
    public void setPrefix(String value) {
        EntityMapper.prefix = value;
    }

    public static String getPrefix() {
        return prefix;
    }

    // ENTITY -> DTO RESPONSE

    public static DtoEmployee entityToDtoEmployee(Employee employee) {
        DtoEmployee dtoEmployee = new DtoEmployee();
        dtoEmployee.setId(employee.getId());
        dtoEmployee.setTypeIdentification(employee.getTypeIdentification());
        dtoEmployee.setIdentification(employee.getIdentification());
        dtoEmployee.setName(employee.getName());
        dtoEmployee.setLastName(employee.getLastName());
        dtoEmployee.setPhone(employee.getPhone());
        dtoEmployee.setCountryDialCode(employee.getCountryDialCode());
        dtoEmployee.setEmail(employee.getEmail());
        dtoEmployee.setStatus(employee.getStatus());
        dtoEmployee.setUserType(employee.getUserType());
        DtoRole dtoRole = entityToDtoRole(employee.getRole());
        dtoEmployee.setRole(dtoRole);
        return dtoEmployee;
    }

    public static DtoClient entityToDtoClient(Client client) {
        DtoClient dtoClient = new DtoClient();
        dtoClient.setId(client.getId());
        dtoClient.setTypeIdentification(client.getTypeIdentification());
        dtoClient.setIdentification(client.getIdentification());
        dtoClient.setName(client.getName());
        dtoClient.setLastName(client.getLastName());
        dtoClient.setPhone(client.getPhone());
        dtoClient.setCountryDialCode(client.getCountryDialCode());
        dtoClient.setEmail(client.getEmail());
        dtoClient.setStatus(client.getStatus());
        dtoClient.setUserType(client.getUserType());
        dtoClient.setAddress(client.getAddress());
        dtoClient.setType(client.getType());
        return dtoClient;
    }

    public static DtoBeneficiary entityToDtoBeneficiary(Beneficiary beneficiary) {
        DtoBeneficiary dtoBeneficiary = new DtoBeneficiary();
        dtoBeneficiary.setId(beneficiary.getId());
        dtoBeneficiary.setTypeIdentification(beneficiary.getTypeIdentification());
        dtoBeneficiary.setIdentification(beneficiary.getIdentification());
        dtoBeneficiary.setName(beneficiary.getName());
        dtoBeneficiary.setLastName(beneficiary.getLastName());
        dtoBeneficiary.setPhone(beneficiary.getPhone());
        dtoBeneficiary.setCountryDialCode(beneficiary.getCountryDialCode());
        dtoBeneficiary.setEmail(beneficiary.getEmail());
        dtoBeneficiary.setStatus(beneficiary.getStatus());
        dtoBeneficiary.setUserType(beneficiary.getUserType());
        dtoBeneficiary.setKinship(beneficiary.getKinship());
        return dtoBeneficiary;
    }

    public static DtoRole entityToDtoRole(Role role) {
        DtoRole dtoRole = new DtoRole();
        dtoRole.setId(role.getId());
        dtoRole.setName(role.getName());
        dtoRole.setCode(role.getCode());
        dtoRole.setDescription(role.getDescription());
        dtoRole.setStatus(role.getStatus());
        dtoRole.setPermissions(role.getPermissions());
        return dtoRole;
    }

    public static DtoUser entityToDtoUser(User user) {
        DtoUser dtoUser = new DtoUser();
        dtoUser.setIdentification(user.getIdentification());
        dtoUser.setTypeIdentification(user.getTypeIdentification());
        dtoUser.setId(user.getId());
        dtoUser.setName(user.getName());
        dtoUser.setLastName(user.getLastName());
        dtoUser.setPhone(user.getPhone());
        dtoUser.setCountryDialCode(user.getCountryDialCode());
        dtoUser.setEmail(user.getEmail());
        return dtoUser;
    }

    // DTOREQUEST -> ENTITY
    public static Employee dtoRequestEmployeeToEntity(DtoRequestEmployee dtoRequestEmployee) {
        Employee employee = new Employee();
        employee.setTypeIdentification(dtoRequestEmployee.getTypeIdentification());
        employee.setIdentification(dtoRequestEmployee.getIdentification());
        employee.setName(dtoRequestEmployee.getName());
        employee.setLastName(dtoRequestEmployee.getLastName());
        employee.setPhone(dtoRequestEmployee.getPhone());
        employee.setCountryDialCode(dtoRequestEmployee.getCountryDialCode());
        employee.setEmail(dtoRequestEmployee.getEmail());
        employee.setPassword(dtoRequestEmployee.getPassword());
        employee.setStatus(dtoRequestEmployee.getStatus());
        employee.setUserType(UserType.employee);
        String prefixLogin = EntityMapper.getPrefix();
        employee.setLogin(prefixLogin.concat(dtoRequestEmployee.getIdentification()));
        return employee;
    }

    public static Client dtoRequestClientToEntity(DtoRequestClient dtoRequestClient) {
        Client client = new Client();
        client.setTypeIdentification(dtoRequestClient.getTypeIdentification());
        client.setIdentification(dtoRequestClient.getIdentification());
        client.setName(dtoRequestClient.getName());
        client.setLastName(dtoRequestClient.getLastName());
        client.setPhone(dtoRequestClient.getPhone());
        client.setCountryDialCode(dtoRequestClient.getCountryDialCode());
        client.setEmail(dtoRequestClient.getEmail());
        client.setPassword(dtoRequestClient.getPassword());
        client.setStatus(dtoRequestClient.getStatus());
        client.setUserType(UserType.client);
        client.setAddress(dtoRequestClient.getAddress());
        client.setType(TypeClient.contizing);
        client.setLogin(dtoRequestClient.getIdentification());
        return client;
    }

    public static Beneficiary dtoRequestBeneficiaryToEntity(DtoRequestBeneficiary dtoRequestBeneficiary) {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setTypeIdentification(dtoRequestBeneficiary.getTypeIdentification());
        beneficiary.setIdentification(dtoRequestBeneficiary.getIdentification());
        beneficiary.setName(dtoRequestBeneficiary.getName());
        beneficiary.setLastName(dtoRequestBeneficiary.getLastName());
        beneficiary.setPhone(dtoRequestBeneficiary.getPhone());
        beneficiary.setCountryDialCode(dtoRequestBeneficiary.getCountryDialCode());
        beneficiary.setEmail(dtoRequestBeneficiary.getEmail());
        beneficiary.setStatus(dtoRequestBeneficiary.getStatus());
        beneficiary.setUserType(TypeClient.beneficiary);
        beneficiary.setKinship(dtoRequestBeneficiary.getKinship());
        return beneficiary;
    }

    public static Role dtoRequestRoleToEntity(DtoRequestRole dtoRequestRole) {
        Role role = new Role();
        role.setName(dtoRequestRole.getName());
        role.setCode(dtoRequestRole.getCode());
        role.setDescription(dtoRequestRole.getDescription());
        role.setStatus(dtoRequestRole.getStatus());
        role.setPermissions(dtoRequestRole.getPermissions());
        return role;
    }

}
