package com.vulcanium.hrassociation.controller;

import com.vulcanium.hrassociation.model.Employee;
import com.vulcanium.hrassociation.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Create - Add a new employee
     *
     * @param employee An Employee object
     * @return The Employee object saved
     */
    @PostMapping("employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    /**
     * Read - Get one employee
     *
     * @param id The id of the employee
     * @return An Employee object
     */
    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable final Long id) {
        Optional<Employee> employee = employeeService.getEmployee(id);
        return employee.orElse(null);
    }

    /**
     * Read - Get all employees
     *
     * @return An Iterable object of Employee
     */
    @GetMapping("/employees")
    public Iterable<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    /**
     * Update - Update an existing employee
     *
     * @param id          The id of the Employee to update
     * @param newEmployee The new Employee object replacing the old one
     * @return The Employee object updated
     */
    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable final Long id, @RequestBody Employee newEmployee) {
        Optional<Employee> emp = employeeService.getEmployee(id);
        Employee currentEmployee = emp.orElse(null);

        if (currentEmployee == null) {
            return null;
        }

        String firstName = newEmployee.getFirstName();
        if (StringUtils.isNotBlank(firstName)) {
            currentEmployee.setFirstName(firstName);
        }

        String lastName = newEmployee.getLastName();
        if (StringUtils.isNotBlank(lastName)) {
            currentEmployee.setLastName(lastName);
        }

        String mail = newEmployee.getMail();
        if (StringUtils.isNotBlank(mail)) {
            currentEmployee.setMail(mail);
        }

        String password = newEmployee.getPassword();
        if (StringUtils.isNotBlank(password)) {
            currentEmployee.setPassword(password);
        }

        return employeeService.saveEmployee(currentEmployee);
    }

    /**
     * Delete - Delete an employee
     *
     * @param id The id of the Employee to delete
     */
    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable final Long id) {
        employeeService.deleteEmployee(id);
    }
}
