package com.practice.service;

import com.practice.custom.exception.BusinessException;
import com.practice.exception.ResourceNotFoundException;
import com.practice.model.Employee;
import com.practice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee emp){
        if(emp.getName().isEmpty()||emp.getName().length()==0)
            throw new BusinessException("701","Name should not be empty or null");
        try{
            return employeeRepository.save(emp);
        }catch(IllegalArgumentException e){
            throw new BusinessException("702","Data should not be empty"+e.getMessage());
        }catch(Exception e){
            throw new BusinessException("703","Something went wrong in Service Layer while saving the employee"+e.getMessage());
        }

    }

    public List<Employee> getAllEmployee(){
        List<Employee> empList = null;
        try{
            empList = (List<Employee>)  employeeRepository.findAll();
        }catch (Exception e){
            throw new BusinessException("705","Something went wrong in Service Layer while fetching all employees"+e.getMessage());
        }
        if(empList.isEmpty()){
            throw new BusinessException("704","Data is not available");
        }
        return empList;
    }

    public Employee getEmployee(int empId){
        try {
            return employeeRepository.findById(empId).get();
        }catch (IllegalArgumentException e){
            throw new BusinessException("706","Provided Id is null"+e.getMessage());
        }catch (NoSuchElementException e){
            throw new BusinessException("707","Provided Id is not valid"+e.getMessage());
        }catch(Exception e){
            throw new BusinessException("708","Something went wrong in Service Layer while fetching the employee for given id"+e.getMessage());
        }

    }

    public void deleteEmployee(int empId){
        try {
            employeeRepository.deleteById(empId);
        }catch (IllegalArgumentException e){
            throw new BusinessException("706","Provided Id is null"+e.getMessage());
        }catch (NoSuchElementException e) {
            throw new BusinessException("707", "Provided Id is not valid" + e.getMessage());
        }catch(Exception e){
            throw new BusinessException("709","Something went wrong in Service Layer while deleting the employee"+e.getMessage());
        }
    }

    public Employee updateEmployee(Employee emp){
        if(emp.getName().isEmpty()||emp.getName().length()==0)
            throw new BusinessException("701","Name should not be empty or null");
        try{
            Employee oldEmp = employeeRepository.findById(emp.getId()).get();
            oldEmp.setId(emp.getId());
            oldEmp.setName(emp.getName());
            oldEmp.setAddress(emp.getAddress());
            Employee updatedEmp = oldEmp;
            return employeeRepository.save(updatedEmp);
        }catch(IllegalArgumentException e){
            throw new BusinessException("702","Data should not be empty"+e.getMessage());
        }catch(Exception e){
            throw new BusinessException("710","Something went wrong in Service Layer while updating the employee"+e.getMessage());
        }
    }
}
