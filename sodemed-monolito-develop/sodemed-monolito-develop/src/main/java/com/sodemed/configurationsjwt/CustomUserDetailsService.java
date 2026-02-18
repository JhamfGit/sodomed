package com.sodemed.configurationsjwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sodemed.models.users.Employee;
import com.sodemed.models.users.User;
import com.sodemed.models.users.enums.StatusUser;
import com.sodemed.models.users.enums.UserType;
import com.sodemed.repositories.users.EmployeeRepository;
import com.sodemed.repositories.users.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLoginAndStatus(username, StatusUser.active)
                .orElseThrow(
                        () -> new UsernameNotFoundException("No se encontró usuario con identificación " + username));
        List<String> permissions = new ArrayList<>();
        if (user.getUserType() == UserType.employee) {
            Employee employee = employeeRepository.findByEmail(user.getEmail()).orElse(null);
            if (employee != null && employee.getRole() != null) {
                permissions = employee.getRole().getPermissions();
            }
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                AuthorityUtils.createAuthorityList(permissions));
    }
}