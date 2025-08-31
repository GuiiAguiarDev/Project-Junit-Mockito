package com.bank.bankSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeTests {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeService employeeService;

	@Test
	void testSaveEmployee() {
		Employee emp = new Employee();
		emp.setName("Guilherme");
		emp.setAge(28);
		emp.setAddress("Rua do Sucesso");
		emp.setEmail("guilherme@hotmail.com");
		emp.setProfession("Programador");
		emp.setSalary(new BigDecimal("10000.00"));

		when(employeeRepository.save(any(Employee.class))).thenReturn(emp);

		Employee saved = employeeService.save(emp);
		assertNotNull(saved);
		assertEquals("Guilherme", saved.getName());
		assertEquals(28, saved.getAge());

		assertEquals("Rua do Sucesso", saved.getAddress());

		assertEquals("guilherme@hotmail.com", saved.getEmail());

		assertEquals("Programador", saved.getProfession());
		assertEquals(0, saved.getSalary().compareTo(new BigDecimal("10000.00")));

		verify(employeeRepository, times(1)).save(saved);

	}

	@Test
	void testUpdateEmployee() {
		Employee empExisting = new Employee(1L, "Guilherme", 28, "Rua do Sucesso", "guilherme@hotmail.com",
				"Programador", new BigDecimal("10000.00"));
		Employee update = new Employee(1L, "Guilherme Aguiar", 28, "Avenida do Sucesso", "guilherme@hotmail.com",
				"Programador", new BigDecimal("10000.00"));

		when(employeeRepository.findById(1L)).thenReturn(Optional.of(empExisting));
		when(employeeRepository.save(any(Employee.class))).thenReturn(empExisting);

		Employee existing = employeeService.updateEmployee(1L, update);

		assertNotNull(existing);
		assertEquals("Guilherme Aguiar", existing.getName());
		assertEquals(28, existing.getAge());

		assertEquals("Avenida do Sucesso", existing.getAddress());

		assertEquals("guilherme@hotmail.com", existing.getEmail());

		assertEquals("Programador", existing.getProfession());
		assertEquals(0, existing.getSalary().compareTo(new BigDecimal("10000.00")));

		verify(employeeRepository, times(1)).findById(1L);
		verify(employeeRepository, times(1)).save(existing);

	}

	@Test
	void testFindEmployeeById() {
		Employee emp = new Employee(1L, "Guilherme", 28, "Avenida do Sucesso", "guilherme@hotmail.com", "Programador",
				new BigDecimal("10000.00"));

		when(employeeRepository.findById(1L)).thenReturn(Optional.of(emp));

		Employee resultId = employeeService.findById(1L);

		assertNotNull(resultId);
		assertEquals(1L, resultId.getId());

		verify(employeeRepository, times(1)).findById(1L);

	}

	@Test
	void testDeleteEmployee() {
		Employee emp = new Employee();
		emp.setId(1L);

		when(employeeRepository.findById(1L)).thenReturn(Optional.of(emp));
		employeeService.deleteEmployee(1L);

		verify(employeeRepository, times(1)).delete(emp);

	}

}
