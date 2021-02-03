package com.example.pma.dao;

import com.example.pma.entities.Employee;
import com.example.pma.entities.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Override
    List<Employee> findAll();
}
