package com.practice.controller;

import com.practice.custom.exception.BusinessException;
import com.practice.custom.exception.ControllerException;
import com.practice.model.Employee;
import com.practice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.Configuration;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> saveEmployee(@RequestBody Employee emp){
        try {
            return new ResponseEntity<Employee>(employeeService.saveEmployee(emp), HttpStatus.CREATED);
        }catch (BusinessException be){
            ControllerException ce = new ControllerException(be.getErrorCode(),be.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            ControllerException ce = new ControllerException("711","Something went wrong in controller layer while saving Employee");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping
    public ResponseEntity<?> getAllEmployee(){
        try{
            return new ResponseEntity<List<Employee>>(employeeService.getAllEmployee(), HttpStatus.OK);
        }catch (BusinessException be){
            ControllerException ce = new ControllerException(be.getErrorCode(),be.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            ControllerException ce = new ControllerException("712","Something went wrong in controller layer while fetching all Employee");
            return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{empId}")
    public ResponseEntity<?> getEmployee(@PathVariable("empId") int empId){
        try{
            return new ResponseEntity<Employee>(employeeService.getEmployee(empId), HttpStatus.OK);
        }catch (BusinessException be){
            ControllerException ce = new ControllerException(be.getErrorCode(),be.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            ControllerException ce = new ControllerException("713","Something went wrong in controller layer while fetching Employee by Id");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping
    public ResponseEntity<?> updateEmployee(@RequestBody Employee emp){
        try{
            return new ResponseEntity<Employee>(employeeService.updateEmployee(emp), HttpStatus.ACCEPTED);
        }catch (BusinessException be){
            ControllerException ce = new ControllerException(be.getErrorCode(),be.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            ControllerException ce = new ControllerException("714","Something went wrong in controller layer while updating Employee");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{empId}")
    public ResponseEntity deleteEmployee(@PathVariable("empId") int empId){
        try{
            employeeService.deleteEmployee(empId);
            return new ResponseEntity(HttpStatus.OK);
        }catch (BusinessException be){
            ControllerException ce = new ControllerException(be.getErrorCode(),be.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            ControllerException ce = new ControllerException("715","Something went wrong in controller layer while deleting Employee");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }
    }

}
