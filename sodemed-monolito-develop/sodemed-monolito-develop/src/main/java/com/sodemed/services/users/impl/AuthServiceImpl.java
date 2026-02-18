package com.sodemed.services.users.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sodemed.configurationsjwt.JwtUtils;
import com.sodemed.dtos.users.LoginUser;
import com.sodemed.dtos.users.request.DtoRequestClient;
import com.sodemed.dtos.users.request.DtoUpdateUser;
import com.sodemed.dtos.users.response.DtoClient;
import com.sodemed.dtos.users.response.DtoForgotPassword;
import com.sodemed.dtos.users.response.DtoSysUser;
import com.sodemed.dtos.users.response.DtoUser;
import com.sodemed.exceptions.NotCreateException;
import com.sodemed.exceptions.NotFoundException;
import com.sodemed.exceptions.NotUpdateException;
import com.sodemed.exceptions.UserLoginNotValid;
import com.sodemed.models.users.Client;
import com.sodemed.models.users.Employee;
import com.sodemed.models.users.User;
import com.sodemed.models.users.enums.StatusUser;
import com.sodemed.models.users.enums.UserType;
import com.sodemed.repositories.users.ClientRepository;
import com.sodemed.repositories.users.EmployeeRepository;
import com.sodemed.repositories.users.UserRepository;
import com.sodemed.services.notifications.NotificationEmailService;
import com.sodemed.services.users.AuthService;
import com.sodemed.utils.PasswordGenerator;
import com.sodemed.utils.users.EntityMapper;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepository userEntityRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private NotificationEmailService notificationEmailService;

    @Value("${prefix.login.admin}")
    private String prefix;

    @Override
    public DtoSysUser login(LoginUser loginRequest) {
        List<String> permissions = new ArrayList<>();
        return handleLogin(loginRequest, UserType.client, permissions);
    }

    @Override
    public DtoSysUser loginEmployee(LoginUser loginRequest) {
        loginRequest.setLogin(prefix.concat(loginRequest.getLogin()));
        List<String> permissions = new ArrayList<>();
        Employee employee = employeeRepository.findByLogin(loginRequest.getLogin()).orElse(null);
        if (employee != null && employee.getRole() != null) {
            permissions = employee.getRole().getPermissions();
        }
        return handleLogin(loginRequest, UserType.employee, permissions);
    }

    @Override
    public DtoClient register(DtoRequestClient client) {
        try {
            if (this.clientRepository.existsByEmailAndUserType(client.getEmail(), UserType.client)) {
                throw new NotCreateException("El correo ya se encuentra registrado");
            }
            if (this.clientRepository.existsByIdentificationAndUserType(client.getIdentification(), UserType.client)) {
                throw new NotCreateException("La identificación ya se encuentra registrada");
            }
            Client clientSave = EntityMapper.dtoRequestClientToEntity(client);
            clientSave.setPassword(passwordEncoder.encode(client.getPassword()));
            clientSave.setStatus(StatusUser.active);
            return EntityMapper.entityToDtoClient(this.clientRepository.save(clientSave));
        } catch (NotCreateException e) {
            throw new NotCreateException(e.getMessage());
        } catch (Exception e) {
            throw new NotCreateException("No se pudo crear el usuario");
        }
    }

    @Override
    public DtoUser update(long id, DtoUpdateUser user) {
        try {
            User userExist = this.userEntityRepository.findById(id)
                    .orElseThrow(() -> new NotUpdateException("Usuario no encontrado"));
            if (userExist != null && !userExist.getEmail().equals(user.getEmail())) {
                if (this.userEntityRepository.existsByEmailAndUserType(user.getEmail(), userExist.getUserType())) {
                    throw new NotUpdateException("El correo ya se encuentra registrado");
                }
            }
            userExist.setId(id);
            userExist.setName(user.getName());
            userExist.setLastName(user.getLastName());
            userExist.setPhone(user.getPhone());
            userExist.setCountryDialCode(user.getCountryDialCode());
            userExist.setEmail(user.getEmail());
            userExist.setPassword(user.getPassword().isBlank() ? userExist.getPassword()
                    : passwordEncoder.encode(user.getPassword()));
            return EntityMapper.entityToDtoUser(this.userEntityRepository.save(userExist));
        } catch (NotUpdateException e) {
            throw new NotUpdateException(e.getMessage());
        } catch (Exception e) {
            throw new NotUpdateException("No se pudo actualizar el usuario con ID " + id);
        }
    }

    private DtoSysUser handleLogin(LoginUser loginRequest, UserType userType, List<String> permissions) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLogin(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userEntityRepository
                .findByLoginAndStatusAndTypeIdentification(authentication.getName(), StatusUser.active,
                        loginRequest.getTypeIdentification())
                .orElseThrow(
                        () -> new NotFoundException(
                                "No se encontró usuario con identificación " + authentication.getName()));
        if (user.getUserType() != userType) {
            throw new UserLoginNotValid("Usuario no autorizado");
        }
        String jwt = jwtUtils.generateClaims(authentication.getName(), user);
        return new DtoSysUser(jwt, user, permissions);
    }

    public DtoForgotPassword initiatePasswordReset(String identification, boolean admin) {
        String newPassword = PasswordGenerator.generatePassword(8);
        User user = this.userEntityRepository
                .findByLoginAndStatus(admin ? prefix.concat(identification) : identification, StatusUser.active)
                .orElseThrow(
                        () -> new NotFoundException("No se encontró usuario con identificación " + identification));
        user.setPassword(passwordEncoder.encode(newPassword));
        this.userEntityRepository.save(user);
        this.notificationEmailService.sendForgotPassword(user.getName() + " " + user.getLastName(), newPassword,
                user.getEmail().trim());
        return new DtoForgotPassword(user.getName(), user.getEmail());
    }

}
