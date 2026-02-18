package com.sodemed.services.users.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sodemed.models.users.Employee;
import com.sodemed.models.users.Role;
import com.sodemed.models.users.enums.StatusRole;
import com.sodemed.models.users.enums.StatusUser;
import com.sodemed.models.users.enums.UserType;
import com.sodemed.repositories.users.EmployeeRepository;
import com.sodemed.repositories.users.RoleRepository;
import com.sodemed.repositories.users.UserRepository;
import com.sodemed.services.users.EmployeeService;
import com.sodemed.utils.users.EntityMapper;
import com.sodemed.dtos.users.request.DtoRequestEmployee;
import com.sodemed.dtos.users.response.DtoEmployee;
import com.sodemed.exceptions.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public DtoEmployee create(DtoRequestEmployee employee) {
        try {
            if (this.employeeRepository.existsByEmailAndUserType(employee.getEmail(), UserType.employee)) {
                throw new NotCreateException("El correo ya se encuentra registrado");
            }
            if (this.employeeRepository.existsByIdentificationAndUserType(employee.getIdentification(),
                    UserType.employee)) {
                throw new NotCreateException("La identificación ya se encuentra registrada");
            }
            Role role = this.roleRepository.findByIdAndStatus(employee.getRoleId(), StatusRole.active).orElse(null);
            if (role == null) {
                throw new NotCreateException("No existe el rol");
            }
            Employee employeeSave = EntityMapper.dtoRequestEmployeeToEntity(employee);
            employeeSave.setRole(role);
            employeeSave.setPassword(passwordEncoder.encode(employee.getPassword()));
            employeeSave.setStatus(StatusUser.active);
            return EntityMapper.entityToDtoEmployee(this.employeeRepository.save(employeeSave));
        } catch (NotCreateException e) {
            throw new NotCreateException(e.getMessage());
        } catch (Exception e) {
            throw new NotCreateException("No se pudo crear el usuario");
        }
    }

    @Override
    public DtoEmployee fetch(long id) {
        try {
            Employee employee = this.employeeRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("No se encontró usuario con ID " + id));
            return EntityMapper.entityToDtoEmployee(employee);
        } catch (Exception e) {
            throw new NotFoundException("Usuario no encontrado");
        }
    }

    @Override
    public DtoEmployee update(long id, DtoRequestEmployee employee) {
        try {
            Employee employeeExist = this.employeeRepository.findById(id)
                    .orElseThrow(() -> new NotUpdateException("Usuario no encontrado"));
            if (employeeExist != null && !employeeExist.getEmail().equals(employee.getEmail())) {
                if (this.userRepository.existsByEmailAndUserType(employee.getEmail(), UserType.employee)) {
                    throw new NotUpdateException("El correo ya se encuentra registrado");
                }
            }
            if (employeeExist != null && !employeeExist.getIdentification().equals(employee.getIdentification())) {
                if (this.userRepository.existsByIdentificationAndUserType(employee.getIdentification(),
                        UserType.employee)) {
                    throw new NotUpdateException("La identificación ya se encuentra registrada");
                }
            }
            Role role = this.roleRepository.findByIdAndStatus(employee.getRoleId(), StatusRole.active).orElse(null);
            if (role == null) {
                throw new NotUpdateException("El rol no existe");
            }
            employeeExist = EntityMapper.dtoRequestEmployeeToEntity(employee);
            employeeExist.setId(id);
            employeeExist.setRole(role);
            employeeExist.setPassword(passwordEncoder.encode(employee.getPassword()));
            return EntityMapper.entityToDtoEmployee(this.employeeRepository.save(employeeExist));
        } catch (NotUpdateException e) {
            throw new NotUpdateException(e.getMessage());
        } catch (Exception e) {
            throw new NotUpdateException("No se pudo actualizar el usuario con ID " + id);
        }
    }

    @Override
    public DtoEmployee delete(long id) {
        try {
            Employee existingEmployee = this.employeeRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("No se encontró usuario con ID " + id));
            existingEmployee.setStatus(StatusUser.inactive);
            return EntityMapper.entityToDtoEmployee(this.employeeRepository.save(existingEmployee));
        } catch (Exception e) {
            throw new NotFoundException("No se pudo eliminar el usuario con ID " + id);
        }
    }

    @Override
    public Page<DtoEmployee> fetchAll(Long id, String identification, String name,
            String lastName, String email, int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page - 1, size);
            return this.employeeRepository
                    .fetchEmployeesByFilters(id, identification, name, lastName,
                            email, pageable)
                    .map(EntityMapper::entityToDtoEmployee);
        } catch (Exception e) {
            throw new NotFoundException("No se encontró usuarios");
        }
    }

    @Override
    public DtoEmployee active(long id) {
        try {
            Employee existingEmployee = this.employeeRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("No se encontró usuario con ID " + id));
            existingEmployee.setStatus(StatusUser.active);
            return EntityMapper.entityToDtoEmployee(this.employeeRepository.save(existingEmployee));
        } catch (Exception e) {
            throw new NotFoundException("No se pudo activar el usuario con ID " + id);
        }
    }

}
