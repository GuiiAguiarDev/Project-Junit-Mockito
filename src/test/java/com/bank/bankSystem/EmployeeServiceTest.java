package com.bank.bankSystem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import org.slf4j.Logger;

import com.employee.exception.ResourceNotFoundException;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;

import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(EmployeeServiceTest.class);

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeService employeeService;

	@Test
	void saveEmployeeTest() {

		Employee emp = new Employee();
		emp.setName("Pedro");
		emp.setAge(10);
		emp.setAddress("Sucesso");
		emp.setEmail("pedro@gmail.com");
		emp.setProfession("Administrador de Redes");
		emp.setSalary(new BigDecimal(6500.00));

		when(employeeRepository.save(any(Employee.class))).thenReturn(emp);

		Employee saved = employeeService.save(emp);

		assertNotNull(saved);
		assertEquals("Pedro", saved.getName());
		assertEquals(10, saved.getAge());
		assertEquals("Sucesso", saved.getAddress());
		assertEquals("pedro@gmail.com", saved.getEmail());
		assertEquals("Administrador de Redes", saved.getProfession());
		assertEquals(0, saved.getSalary().compareTo(new BigDecimal(6500.00)));

	}

	// Verificar quantas vezes a classe foi chamada, nesse exemplo estou verificando
	// quantas vezes meu employeeRepository foi chamado
	// se chamo uma vez e lá em baixo coloco 2 vai dar erro, tem que ser o memso
	// valor
	// fazer verify em cima do repository
	@Test
	void callRepository() {
		try {
			Employee emp = new Employee();

			employeeRepository.save(emp);
			employeeRepository.save(emp);

			verify(employeeRepository, times(2)).save(emp);
		} catch (AssertionError e) {
			logger.error("SaveEmployeeTest faled! Detals: {}", e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.error("saveEmployeeTest unexpected Error!");
		}
	}

	/* Testando Delete Sucess, se deleta certo */
	@Test
	void deleteEmployeeTest() {
		Employee emp = new Employee();
		emp.setId(1L);
		emp.setName("Pedro");

		when(employeeRepository.findById(1L)).thenReturn(Optional.of(emp));

		employeeService.deleteClient(1L);

		// Verifica se o metodo foi chamado uma vez
		verify(employeeRepository, times(1)).delete(emp);

	}

	/*
	 * Testando Delete, se não achar o item para apagar ou seja não achar o id, ele
	 * lança a exceção, da certo por isso fica, verde. Pois o objetivo aqui //é
	 * simular um erro entao tem que dar errado
	 */
	@Test
	void deleteEmployeeTestNotFound() {
		when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> employeeService.deleteClient(1L));
	}

	// Test Update

@Test
void employeeUpdateTest() {
	Employee existing = new Employee(1L,"g", 10, "g", "g", "g", new BigDecimal("7000.00"));
	Employee update = new Employee(1L,"g", 10, "g", "g", "g", new BigDecimal("7000.00")); 
	
	when(employeeRepository.findById(1L)).thenReturn(Optional.of(existing));
	when(employeeRepository.save(existing)).thenReturn(existing);
	
	Employee updating = employeeService.updateClient(1L, update);
	
	assertEquals("g", updating.getName());
	assertEquals(10, updating.getAge());
	assertEquals("g", updating.getAddress());
	assertEquals("g", updating.getEmail());
	assertEquals("g", updating.getProfession());
	assertEquals(new BigDecimal("7000.00"), updating.getSalary());
	
	verify(employeeRepository, times(1)).findById(1L);
	verify(employeeRepository, times(1)).save(existing);

	
	
}

}
