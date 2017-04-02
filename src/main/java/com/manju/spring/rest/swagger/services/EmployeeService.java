package com.manju.spring.rest.swagger.services;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.manju.spring.rest.swagger.exceptions.EmployeeNotFoundException;
import com.manju.spring.rest.swagger.model.Employee;

@Service
public class EmployeeService {

	private ConcurrentHashMap<Integer, Employee> dataMap = new ConcurrentHashMap<>();
	
	private AtomicInteger counter = new AtomicInteger(0);
	
	public List<Employee> getAllEmployees() {
		return dataMap.values().stream().collect(Collectors.toList());
	}
	
	public Employee findByEmployeeId(Integer id) {
		if(!dataMap.containsKey(id)) {
			throw new EmployeeNotFoundException("Employee does not exist for id : " + id);
		}
		return dataMap.values().stream().filter((e) -> e.getId() == id).findFirst().get();
	}
	
	public Integer addEmployee(Employee employee) {
		Integer id = counter.getAndIncrement();
		employee.setId(id);
		dataMap.put(id, employee);
		return id;
	}
	
	public void deleteEmployee(Integer id) {
		if(dataMap.remove(id) == null) {
			throw new EmployeeNotFoundException("Employee does not found for id : " + id);
		}
	}
}
