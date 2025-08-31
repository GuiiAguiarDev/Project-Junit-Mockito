package com.employee.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")

public class EmployeeController {

	private final EmployeeService clientService;

	public EmployeeController(EmployeeService clientService) {
		this.clientService = clientService;
	}

	@PostMapping
	public Employee save(@Valid @RequestBody Employee client) {
		return clientService.save(client);
	}

	@GetMapping
	public List<Employee> list() {
		return clientService.list();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getClientById(@PathVariable Long id) {
		Employee client = clientService.findById(id);
		return ResponseEntity.ok(client);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> clientUpdate(@PathVariable Long id, @RequestBody Employee updatedClient) {
		Employee client = clientService.updateEmployee(id, updatedClient);
		return ResponseEntity.ok(client);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
		clientService.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}
}
