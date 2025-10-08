package com.example.sjks.EmploymentService.controller;

import com.example.sjks.EmploymentService.entity.Employee;
import com.example.sjks.EmploymentService.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.status(201).body(savedEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Optional<Employee> existing = employeeService.getEmployeeById(id);
        if (existing.isPresent()) {
            Employee toUpdate = existing.get();
            toUpdate.setFirstName(employee.getFirstName());
            toUpdate.setLastName(employee.getLastName());
            toUpdate.setEmail(employee.getEmail());
            toUpdate.setPhoneNumber(employee.getPhoneNumber());
            toUpdate.setDepartment(employee.getDepartment());
            Employee updated = employeeService.saveEmployee(toUpdate);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (employeeService.getEmployeeById(id).isPresent()) {
            employeeService.deleteEmployee(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search/email")
    public ResponseEntity<Employee> getEmployeeByEmail(@RequestParam String email) {
        Optional<Employee> employee = employeeService.getEmployeeByEmail(email);
        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search/phone")
    public ResponseEntity<Employee> getEmployeeByPhoneNumber(@RequestParam String phoneNumber) {
        Optional<Employee> employee = employeeService.getEmployeeByPhoneNumber(phoneNumber);
        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search/firstName")
    public List<Employee> getEmployeesByFirstName(@RequestParam String firstName) {
        return employeeService.getEmployeesByFirstName(firstName);
    }

    @GetMapping("/search/department")
    public List<Employee> getEmployeesByDepartment(@RequestParam Long departmentId) {
        return employeeService.getEmployeesByDepartment(departmentId);
    }
}
