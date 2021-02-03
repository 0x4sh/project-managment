package com.example.pma.controllers;

import com.example.pma.dao.EmployeeRepository;
import com.example.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeRepository empRep;

    // mapped with list-employees.html
    @GetMapping
    public String displayEmployees(Model model){
        List<Employee> employeeList = empRep.findAll();
        model.addAttribute("employees",employeeList);
        return "employees/list-employees";
    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "employees/new-employee";
    }

    @PostMapping(value = "/save") // object is received from new-employee.html
    public String createEmployee(Employee employee, Model model){

        //  save to the database using an employee crud repository
        empRep.save(employee);

        // use a redirect to prevent duplicate submissions
        return "redirect:/employees/new";
    }
}
