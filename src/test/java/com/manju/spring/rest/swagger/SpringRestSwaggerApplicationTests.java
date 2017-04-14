package com.manju.spring.rest.swagger;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.manju.spring.rest.swagger.model.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringRestSwaggerApplicationTests {
	
	public static final String REST_SERVICE_URI = "http://localhost:8888/employee";
	public static final String CREDENTIALS = "manjunath:manjunath123";
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testGetAllEmployees() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + new String(Base64.encode(CREDENTIALS.getBytes())));
		HttpEntity<?> request = new HttpEntity<>(headers);
		//List<LinkedHashMap<String, Object>> employeesMap = restTemplate.getForObject(REST_SERVICE_URI + "/list", List.class);
		ResponseEntity<List> responseEntity = restTemplate.exchange(REST_SERVICE_URI + "/list", HttpMethod.GET, request, List.class);
		assertTrue(responseEntity.getBody().size() > 0);
		responseEntity.getBody().stream().forEach(object -> {
			System.out.println(object);
		});
	}
	
	@Test
	public void testFindByEmployeeId() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + new String(Base64.encode(CREDENTIALS.getBytes())));
		HttpEntity<?> request = new HttpEntity<>(headers);
		//Employee employee = restTemplate.getForObject(REST_SERVICE_URI + "/0", Employee.class);
		ResponseEntity<Employee> responseEntity = restTemplate.exchange(REST_SERVICE_URI + "/0", HttpMethod.GET, request, Employee.class);
		assertNotNull(responseEntity.getBody());
		System.out.println(responseEntity.getBody());
	}

	@Test
	public void testAddEmployee() {
		RestTemplate restTemplate = new RestTemplate();
		Employee employee = new Employee();
		employee.setName("Manjunath");
		employee.setAge(30);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + new String(Base64.encode(CREDENTIALS.getBytes())));
		HttpEntity<?> request = new HttpEntity<>(employee, headers);
		//URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/add", employee);
		ResponseEntity<URI> responseEntity = restTemplate.exchange(REST_SERVICE_URI + "/add", HttpMethod.POST, request, URI.class);
		assertNotNull(responseEntity.getHeaders().getLocation().toASCIIString());
		System.out.println(responseEntity.getHeaders().getLocation().toASCIIString());
	}
	
	@Test
	public void testUpdateEmployee() {
		RestTemplate restTemplate = new RestTemplate();
		Employee employee = new Employee();
		employee.setName("Ramesh");
		employee.setAge(32);
		employee.setId(1);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + new String(Base64.encode(CREDENTIALS.getBytes())));
		HttpEntity<?> request = new HttpEntity<>(employee, headers);
		//restTemplate.put(REST_SERVICE_URI + "/3", employee);
		ResponseEntity<Employee> responseEntity = restTemplate.exchange(REST_SERVICE_URI + "/3", HttpMethod.PUT, request, Employee.class);
		assertNotNull(responseEntity.getBody());
		System.out.println(responseEntity.getBody());
	}
	
	@Test
	public void testDeleteEmployee() {
		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Basic " + new String(Base64.encode(CREDENTIALS.getBytes())));
			HttpEntity<?> request = new HttpEntity<>(headers);
			//restTemplate.delete(REST_SERVICE_URI + "/2");
			ResponseEntity<Object> responseEntity = restTemplate.exchange(REST_SERVICE_URI + "/2", HttpMethod.DELETE, request, Object.class);
			assertNotNull(responseEntity.getBody());
		} catch(HttpClientErrorException ex) {
			assertTrue(ex.getStatusCode() == HttpStatus.NOT_FOUND);
		}
	}

}
