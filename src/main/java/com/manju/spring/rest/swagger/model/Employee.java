package com.manju.spring.rest.swagger.model;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Employee", description="Employee Representation")
public class Employee {
	
	@ApiModelProperty(value = "Employee Name", required = true)
	@NotBlank(message = "Employee name should not be empty.")
	private String name;
	
	@ApiModelProperty(value = "Employee unique Id", required = true)
	private Integer id;
	
	@ApiModelProperty(value = "Employee Age", required = true)
	private Integer age;
	
	/*
	 * No-Args Constructor
	 */
	public Employee() {
	}
	
	public Employee(String name, Integer id, Integer age) {
		this.name = name;
		this.id = id;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
