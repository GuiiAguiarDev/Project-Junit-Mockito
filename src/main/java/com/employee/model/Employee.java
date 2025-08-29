package com.employee.model;

import java.math.BigDecimal;

import com.employee.utils.MoneySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	@NotBlank(message = "Name required")
	private String name;
	@NotNull(message = "Age required")
	private Integer age;
	@NotBlank(message = "Address required")
	private String address;

	private String email;
	@NotBlank(message = "Profession required")
	private String profession;
	@NotNull(message = "Salary required")
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal salary;

	public Employee() {
		super();
	}

	public Employee(Long id, String name, Integer age, String address, String email, String profession,
			BigDecimal salary) {

		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.email = email;
		this.profession = profession;
		this.salary = salary;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

}
