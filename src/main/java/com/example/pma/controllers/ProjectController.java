package com.example.pma.controllers;

import com.example.pma.dao.EmployeeRepository;
import com.example.pma.dao.ProjectRepository;
import com.example.pma.entities.Employee;
import com.example.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectRepository proRep;

    @Autowired
    EmployeeRepository empRepo;

    // mapped with list-projects.html
    @GetMapping
    public String displayProjects(Model model) {
        List<Project> projectList = proRep.findAll();
        model.addAttribute("projects", projectList);
        return "projects/list-projects";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model) {

        // "project" is object and should be the same in html file
        Project aProject = new Project();
        model.addAttribute("project", aProject);

        List<Employee> employeeList = empRepo.findAll();
        model.addAttribute("allEmployees", employeeList);

        return "projects/new-project";
    }

    @PostMapping(value = "/save")
    public String createProject(Project project, Model model) {
        proRep.save(project);

        /*// add those employees data received from project form to the DB
        Iterable<Employee> chosenEmployees = empRepo.findAllById(employees);

        for (Employee emp : chosenEmployees) {
            emp.setProjects(project);
            empRepo.save(emp);
        }*/

        // use a redirect to prevent duplicate submissions
        return "redirect:/projects/new";
    }
}
