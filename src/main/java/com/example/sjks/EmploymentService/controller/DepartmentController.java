package com.example.sjks.EmploymentService.controller;

import com.example.sjks.EmploymentService.entity.Department;
import com.example.sjks.EmploymentService.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@GetMapping
	public List<Department> getAllDepartments() {
		return departmentService.getAllDepartments();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
		Optional<Department> department = departmentService.getDepartmentById(id);
		return department.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
		Department savedDepartment = departmentService.saveDepartment(department);
		return ResponseEntity.status(201).body(savedDepartment);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
		Optional<Department> existing = departmentService.getDepartmentById(id);
		if (existing.isPresent()) {
			Department toUpdate = existing.get();
			toUpdate.setName(department.getName());
			Department updated = departmentService.saveDepartment(toUpdate);
			return ResponseEntity.ok(updated);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
		if (departmentService.getDepartmentById(id).isPresent()) {
			departmentService.deleteDepartment(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
