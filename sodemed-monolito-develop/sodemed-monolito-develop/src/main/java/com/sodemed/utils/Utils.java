package com.sodemed.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sodemed.models.email.EmailConfiguration;
import com.sodemed.models.users.Client;
import com.sodemed.models.users.Employee;
import com.sodemed.models.users.Permission;
import com.sodemed.models.users.Role;
import com.sodemed.models.users.enums.StatusRole;
import com.sodemed.models.users.enums.StatusUser;
import com.sodemed.models.users.enums.TypeClient;
import com.sodemed.models.users.enums.TypeIdentification;
import com.sodemed.models.users.enums.UserType;
import com.sodemed.repositories.email.EmailConfigurationRepository;
import com.sodemed.repositories.users.ClientRepository;
import com.sodemed.repositories.users.EmployeeRepository;
import com.sodemed.repositories.users.PermissionRepository;
import com.sodemed.repositories.users.RoleRepository;
import com.sodemed.repositories.users.UserRepository;
import com.sodemed.utils.users.EntityMapper;

@Configuration
public class Utils {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static String prefix;

    @Value("${prefix.login.admin}")
    public static void setPrefix(String value) {
        Utils.prefix = value;
    }

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, ClientRepository clientRepository,
            EmployeeRepository employeeRepository, RoleRepository roleRepository, 
            EmailConfigurationRepository emailConfigurationRepository,PermissionRepository permissionRepository) {
        return args -> {
            if (!employeeRepository.existsByIdentification("11119999")) {
                List<String> permissions = new ArrayList<>();
                permissions.add("admin:sodemed");
                Role role = new Role("ADMIN", "admSodemed", "Rol Administrativo", StatusRole.active, permissions);
                role = roleRepository.save(role);
                Employee admin = new Employee(TypeIdentification.CC, "11119999", "Root",
                        "Admin Medcol", "11119999", "+57",
                        "demo2@demo.com", passwordEncoder.encode("020202020"), StatusUser.active);
                admin.setLogin(prefix+"11119999");
                admin.setRole(role);
                admin.setUserType(UserType.employee);
                employeeRepository.save(admin);

                List<Permission> permissionList = new ArrayList<>();
                permissionList.add(new Permission("admin:sodemed"));
                permissionList.add(new Permission("clients:sodemed"));
                permissionList.add(new Permission("employees:sodemed"));
                permissionList.add(new Permission("roles:sodemed"));
                permissionList.add(new Permission("medications:sodemed"));

                permissionRepository.saveAll(permissionList);
            }

            if (emailConfigurationRepository.count() == 0) {
                EmailConfiguration config = new EmailConfiguration();
                config.setHost("smtp.gmail.com");
                config.setPort("587");
                config.setUsername("noreplaysaludmedcol@gmail.com");
                config.setPassword("bbxc wnkd dkny cict");
                emailConfigurationRepository.save(config);
            }
        };
    }

}