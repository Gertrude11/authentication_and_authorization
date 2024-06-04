package com.example.SpringSecurity.service;

import com.example.SpringSecurity.entity.Employee;
import com.example.SpringSecurity.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    //define field
    private EmployeeRepository repo;

    //constructor injection
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Employee> findAll() {
        return repo.findAll();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = repo.findById(theId);

        Employee theEmployee = null;

        if(result.isPresent()){
            theEmployee = result.get();
        } else{
            throw new RuntimeException("Employee id not found - " + theId);
        }
        return theEmployee;
    }

    @Transactional
    @Override
    public Employee save(Employee theEmployee) {
        return repo.save(theEmployee);
    }
    @Transactional
    @Override
    public void deleteById(int theId) {
        repo.deleteById(theId);

    }
}
