package com.sodemed.services.users;

import com.sodemed.dtos.users.LoginUser;
import com.sodemed.dtos.users.request.DtoRequestClient;
import com.sodemed.dtos.users.request.DtoUpdateUser;
import com.sodemed.dtos.users.response.DtoClient;
import com.sodemed.dtos.users.response.DtoForgotPassword;
import com.sodemed.dtos.users.response.DtoSysUser;
import com.sodemed.dtos.users.response.DtoUser;

public interface AuthService {

    DtoSysUser login(LoginUser loginRequest);

    DtoSysUser loginEmployee(LoginUser loginRequest);

    DtoClient register(DtoRequestClient client);

    DtoForgotPassword initiatePasswordReset(String identification, boolean admin);

    DtoUser update(long id, DtoUpdateUser user);

}
