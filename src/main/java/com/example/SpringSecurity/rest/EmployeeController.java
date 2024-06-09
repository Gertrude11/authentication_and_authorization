package com.example.SpringSecurity.rest;

import com.example.SpringSecurity.entity.Employee;
import com.example.SpringSecurity.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class EmployeeController {
    private EmployeeService employeeService;

   @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    //add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee (@PathVariable int employeeId){
       Employee theEmployee = employeeService.findById(employeeId);

       if(theEmployee == null){
           throw new  RuntimeException("Employee id not found - "+ employeeId);
       }
       return theEmployee;
    }

    //add mapping for POST /employees - add new employees
    //@RequestBody because we are requesting body as JSON and this handles binding that JSON
    @PostMapping("/employees")
    public Employee addEmployee (@RequestBody Employee theEmployee){

       //in case they pass an id in JSON .... set id to 0
        // this is to force a save of new item ... instead of update
       theEmployee.setId(0);
       Employee savedEmployee = employeeService.save(theEmployee);
       return  savedEmployee;
    }

    // add mapping for PUT /employees - update employee
    @PutMapping("/employees")
    public Employee updateEmployee (@RequestBody Employee theEmployee){

       Employee updatedEmployee = employeeService.save(theEmployee);

       return updatedEmployee;
    }

    // add mapping for DELETE /employees/{employeeId} - delete employee
    @DeleteMapping("/employees/{employeeId}")
    public  String deleteEmployee (@PathVariable int employeeId){

       Employee tempEmployee = employeeService.findById(employeeId);

        if(tempEmployee == null){
            throw new  RuntimeException("Employee id not found - "+ employeeId);
        }
        employeeService.deleteById(employeeId);

        return "Deleted employee id - " + employeeId;
    }
}
