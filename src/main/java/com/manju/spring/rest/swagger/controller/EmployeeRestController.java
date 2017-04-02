package com.manju.spring.rest.swagger.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.manju.spring.rest.swagger.model.Employee;
import com.manju.spring.rest.swagger.services.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "/employee", description = "Employee Path")
public class EmployeeRestController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@RequestMapping(value = "/list", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
	@ApiOperation( value = "List all the employees", notes = "List all the employees", response = Employee.class, responseContainer = "List")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
	
	
	
	@RequestMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
	@ApiOperation(value = "Find by Employee Id", notes = "Find by Employee Id",  response = Employee.class)
	@ApiResponses(
			@ApiResponse(code =  404, message = "Employee does not exist for id.")
	)
	public Employee findByEmployeeId(@ApiParam( value = "Employee id to lookup", required = true) @PathVariable("id") String id) {
		return employeeService.findByEmployeeId(Integer.parseInt(id));
	}
	
	
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Add new employee", notes = "Add new employee")
	@ApiResponses(
			@ApiResponse(code = 201, message = "Employee Created Successfully")
	)
	public ResponseEntity<?> addEmployee(@Valid @RequestBody Employee employee) {
		Integer id = employeeService.addEmployee(employee);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Update Employee for id", notes = "Update Employee for id", response = Employee.class)
	@ApiResponses(
			@ApiResponse(code = 404, message = "Employee does not exist for id.")
	)
	public Employee updateEmployee(@ApiParam( value = "Employee id to update", required = true) @PathVariable("id") String id, 
			@Valid @RequestBody Employee employee) {
		Employee existingEmployee = employeeService.findByEmployeeId(Integer.parseInt(id));
		if(employee.getName() != null) {
			existingEmployee.setName(employee.getName());
		}
		if(employee.getAge() != null) {
			existingEmployee.setAge(employee.getAge());
		}
		return existingEmployee;
	}
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete Employee for id", notes = "Delete Employee for id")
	@ApiResponses(
			@ApiResponse(code = 404, message = "Employee does not exist for id.")
	)
	public ResponseEntity<?> deleteEmployee(@ApiParam( value = "Employee id to delete", required = true) @PathVariable("id") String id) {
		employeeService.deleteEmployee(Integer.parseInt(id));
		return ResponseEntity.ok().build();
	}

}
