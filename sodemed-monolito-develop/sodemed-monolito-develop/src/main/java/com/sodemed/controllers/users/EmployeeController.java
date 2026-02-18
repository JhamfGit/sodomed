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

import com.sodemed.dtos.users.request.DtoRequestEmployee;
import com.sodemed.dtos.users.response.DtoEmployee;
import com.sodemed.services.users.EmployeeService;
import com.sodemed.utils.response.ResponseData;
import com.sodemed.utils.response.ResponseDataPageable;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/")
    public ResponseData<DtoEmployee> createEmployee(@RequestBody DtoRequestEmployee employee) {
        return new ResponseData<DtoEmployee>(HttpStatus.CREATED, this.employeeService.create(employee));
    }

    @GetMapping(value = "/{id}")
    public ResponseData<DtoEmployee> fetchEmployee(@PathVariable(name = "id") long id) {
        return new ResponseData<DtoEmployee>(HttpStatus.OK, this.employeeService.fetch(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseData<DtoEmployee> updateEmployee(@PathVariable(name = "id") long id,
            @RequestBody DtoRequestEmployee employee) {
        return new ResponseData<DtoEmployee>(HttpStatus.OK, this.employeeService.update(id, employee));
    }

    @GetMapping(value = "/")
    public ResponseDataPageable<DtoEmployee> getAll(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String identification,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam int page,
            @RequestParam int size) {
        return new ResponseDataPageable<DtoEmployee>(HttpStatus.OK,
                this.employeeService.fetchAll(id, identification, name, lastName, email, page, size));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseData<DtoEmployee> deleteEmployee(@PathVariable(name = "id") long id) {
        return new ResponseData<DtoEmployee>(HttpStatus.OK, this.employeeService.delete(id));
    }

    @PutMapping(value = "active/{id}")
    public ResponseData<DtoEmployee> activeEmployee(@PathVariable(name = "id") long id) {
        return new ResponseData<DtoEmployee>(HttpStatus.OK, this.employeeService.active(id));
    }

}
