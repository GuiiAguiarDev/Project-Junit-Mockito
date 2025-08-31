package com.employee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.employee.exception.ResourceNotFoundException;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository clientRepository;

	public EmployeeService(EmployeeRepository clientRepository) {
		this.clientRepository = clientRepository;

	}

	public Employee save(Employee client) {
		if (client.getEmail() == null || client.getEmail().isEmpty()) {
			throw new IllegalArgumentException("Email not found");
		}
		return clientRepository.save(client);

	}

	public List<Employee> list() {
		return clientRepository.findAll();

	}

	public Employee findById(Long id) {
		return clientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));

	}

	public Employee updateEmployee(Long id, Employee updatedClient) {
		Employee existingUser = clientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid update - Client not found with id: " + id));

		existingUser.setName(updatedClient.getName());
		existingUser.setAge(updatedClient.getAge());
		existingUser.setEmail(updatedClient.getEmail());
		existingUser.setAddress(updatedClient.getAddress());
		existingUser.setProfession(updatedClient.getProfession());
		existingUser.setSalary(updatedClient.getSalary());

		return clientRepository.save(existingUser);

	}

	public void deleteEmployee(Long id) {

		Employee client = clientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid delete - Client not found with id: " + id));

		clientRepository.delete(client);

	}

}
