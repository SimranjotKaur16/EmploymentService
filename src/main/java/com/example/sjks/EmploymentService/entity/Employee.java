package com.example.sjks.EmploymentService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(nullable = false , unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    private String jobTitle;

    private int salary;

    private LocalDate hireDate;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;
}
