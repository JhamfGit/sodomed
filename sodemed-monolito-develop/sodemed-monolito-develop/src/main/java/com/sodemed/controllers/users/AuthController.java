package com.sodemed.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sodemed.dtos.users.LoginUser;
import com.sodemed.dtos.users.request.DtoRequestClient;
import com.sodemed.dtos.users.request.DtoUpdateUser;
import com.sodemed.dtos.users.response.DtoClient;
import com.sodemed.dtos.users.response.DtoForgotPassword;
import com.sodemed.dtos.users.response.DtoSysUser;
import com.sodemed.dtos.users.response.DtoUser;
import com.sodemed.services.users.AuthService;
import com.sodemed.utils.response.ResponseData;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseData<DtoSysUser> authenticateUser(
            @RequestBody LoginUser loginRequest) {
        return new ResponseData<DtoSysUser>(HttpStatus.OK, this.authService.login(loginRequest));
    }

    @PostMapping("/admin/login")
    public ResponseData<DtoSysUser> authenticateUserEmployee(
            @RequestBody LoginUser loginRequest) {
        return new ResponseData<DtoSysUser>(HttpStatus.OK, this.authService.loginEmployee(loginRequest));
    }

    @PostMapping(value = "/register")
    public ResponseData<DtoClient> createClient(@RequestBody DtoRequestClient client) {
        return new ResponseData<DtoClient>(HttpStatus.CREATED, this.authService.register(client));
    }

    @PostMapping("/forgot-password")
    public ResponseData<DtoForgotPassword> forgotPassword(@RequestBody String identification) {
        return new ResponseData<DtoForgotPassword>(HttpStatus.OK,
                authService.initiatePasswordReset(identification, false));
    }
    
    @PostMapping("/admin/forgot-password")
    public ResponseData<DtoForgotPassword> adminForgotPassword(@RequestBody String identification) {
        return new ResponseData<DtoForgotPassword>(HttpStatus.OK,
                authService.initiatePasswordReset(identification, true));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseData<DtoUser> updateUser(@PathVariable(name = "id") long id,
            @RequestBody DtoUpdateUser user) {
        return new ResponseData<DtoUser>(HttpStatus.OK, this.authService.update(id, user));
    }
}
