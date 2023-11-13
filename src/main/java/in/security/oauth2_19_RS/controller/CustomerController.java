package in.security.oauth2_19_RS.controller;

import java.util.List;

import javax.validation.Valid;

import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import in.security.oauth2_19_RS.entity.Customer;
import in.security.oauth2_19_RS.model.ICustomerCreateRequest;
import in.security.oauth2_19_RS.model.ICustomerUpdateRequest;

public interface CustomerController {

	@PostMapping("/create")
	public ResponseEntity<String> create(
			@Valid @Validated(ICustomerCreateRequest.class) @RequestBody Customer customer);

	@GetMapping("/get")
	public ResponseEntity<List<Customer>> getAllCustomer();

	@DeleteMapping("/deleteByUuid/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable String id);

	@PutMapping("/updateByUuid")
	public ResponseEntity<String> updateCustomer(
			@Valid @Validated(ICustomerUpdateRequest.class) @RequestBody Customer customer)
			throws JsonMappingException, JsonProcessingException, ParseException;

}
