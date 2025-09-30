package com.example.sjks.EmploymentService.repository;

import com.example.sjks.EmploymentService.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByPhoneNumber(String phoneNumber);

    List<Employee> findByFirstName(String firstName);

    List<Employee> findByJobTitle(String jobTitle);

    List<Employee> findByDepartmentId(Long departmentId);

    List<Employee> findByFirstNameContainingAndLastNameContaining(String firstName, String lastName);

}