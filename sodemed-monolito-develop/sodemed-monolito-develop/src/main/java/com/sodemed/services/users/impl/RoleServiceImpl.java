package com.sodemed.services.users.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sodemed.dtos.users.request.DtoRequestRole;
import com.sodemed.dtos.users.response.DtoRole;
import com.sodemed.exceptions.NotCreateException;
import com.sodemed.exceptions.NotFoundException;
import com.sodemed.exceptions.NotUpdateException;
import com.sodemed.models.users.Role;
import com.sodemed.models.users.enums.StatusRole;
import com.sodemed.repositories.users.PermissionRepository;
import com.sodemed.repositories.users.RoleRepository;
import com.sodemed.services.users.RoleService;
import com.sodemed.utils.users.EntityMapper;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public DtoRole create(DtoRequestRole role) {
        try {
            if (this.roleRepository.existsByCode(role.getCode())) {
                throw new NotCreateException("Duplicate Code");
            }
            if (this.roleRepository.existsByName(role.getName())) {
                throw new NotCreateException("Duplicate Name");
            }
            Role roleSave = EntityMapper.dtoRequestRoleToEntity(role);
            roleSave.setStatus(StatusRole.active);
            return EntityMapper.entityToDtoRole(this.roleRepository.save(roleSave));
        } catch (NotCreateException e) {
            throw new NotCreateException(e.getMessage());
        } catch (Exception e) {
            throw new NotCreateException("Role not create");
        }
    }

    @Override
    public DtoRole update(long id, DtoRequestRole role) {
        try {
            Role roleExist = this.roleRepository.findById(id)
                    .orElseThrow(() -> new NotUpdateException("Rol no encontrado"));
            if (roleExist != null) {
                if (!roleExist.getCode().equals(role.getCode()) && this.roleRepository.existsByCode(role.getCode())) {
                    throw new NotUpdateException("La codigo ya se encuentra registrado");
                }
                if (!roleExist.getName().equals(role.getName()) && this.roleRepository.existsByName(role.getName())) {
                    throw new NotUpdateException("La nombre ya se encuentra registrado");
                }
            }
            roleExist = EntityMapper.dtoRequestRoleToEntity(role);
            roleExist.setId(id);
            return EntityMapper.entityToDtoRole(this.roleRepository.save(roleExist));
        } catch (NotUpdateException e) {
            throw new NotUpdateException(e.getMessage());
        } catch (Exception e) {
            throw new NotUpdateException("No se pudo actualizar el rol con ID " + id);
        }
    }

    @Override
    public DtoRole delete(long id) {
        try {
            Role existingRole = this.roleRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("No se encontró rol con ID " + id));
            existingRole.setStatus(StatusRole.inactive);
            return EntityMapper.entityToDtoRole(this.roleRepository.save(existingRole));
        } catch (Exception e) {
            throw new NotFoundException("No se pudo actualizar el rol con ID " + id);
        }
    }

    @Override
    public List<DtoRole> fetchAll() {
        try {
            List<Role> roles = this.roleRepository.findAll();
            List<DtoRole> dtoRoles = roles.stream().map(EntityMapper::entityToDtoRole)
                    .collect(Collectors.toList());
            return dtoRoles;
        } catch (Exception e) {
            throw new NotFoundException("No se encontró roles");
        }
    }


    @Override
    public List<DtoRole> fetchByStatus(StatusRole statusRole) {
        try {
            List<Role> roles = this.roleRepository.findByStatus(statusRole);
            List<DtoRole> dtoRoles = roles.stream().map(EntityMapper::entityToDtoRole)
                    .collect(Collectors.toList());
            return dtoRoles;
        } catch (Exception e) {
            throw new NotFoundException("No se encontró roles");
        }
    }

    @Override
    public List<String> getPermissions() {
        try {
            return this.permissionRepository.findAll().stream()
                    .map(permission -> permission.getName()).toList();
        } catch (Exception e) {
            throw new NotFoundException("No se encontró permisos");
        }
    }

}
