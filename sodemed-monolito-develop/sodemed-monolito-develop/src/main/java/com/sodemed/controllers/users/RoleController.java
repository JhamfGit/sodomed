package com.sodemed.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sodemed.dtos.users.request.DtoRequestRole;
import com.sodemed.dtos.users.response.DtoRole;
import com.sodemed.models.users.enums.StatusRole;
import com.sodemed.services.users.RoleService;
import com.sodemed.utils.response.ResponseData;
import com.sodemed.utils.response.ResponseDataList;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping(value = "/")
    public ResponseData<DtoRole> createRole(@RequestBody DtoRequestRole role) {
        return new ResponseData<DtoRole>(HttpStatus.CREATED, this.roleService.create(role));
    }

    @GetMapping(value = "/")
    public ResponseDataList<DtoRole> getAll() {
        return new ResponseDataList<DtoRole>(HttpStatus.OK, this.roleService.fetchAll());
    }

    @GetMapping(value = "/by-status")
    public ResponseDataList<DtoRole> getByStatus(@RequestParam(name= "status") StatusRole status) {
        return new ResponseDataList<DtoRole>(HttpStatus.OK, this.roleService.fetchByStatus(status));
    }

    @PutMapping(value = "/{id}")
    public ResponseData<DtoRole> updateRole(@PathVariable(name = "id") long id, @RequestBody  DtoRequestRole role) {
        return new ResponseData<DtoRole>(HttpStatus.OK, this.roleService.update(id, role));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseData<DtoRole> deleteRole(@PathVariable(name = "id") long id) {
        return new ResponseData<DtoRole>(HttpStatus.OK, this.roleService.delete(id));
    }

    @GetMapping("/permissions")
    public ResponseDataList<String> getAllPermission() {
        return new ResponseDataList<String>(HttpStatus.OK, this.roleService.getPermissions());
    }
}
