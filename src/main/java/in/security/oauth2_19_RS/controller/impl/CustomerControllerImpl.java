package in.security.oauth2_19_RS.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.security.oauth2_19_RS.controller.CustomerController;
import in.security.oauth2_19_RS.entity.Customer;
import in.security.oauth2_19_RS.service.CustomerService;

@RestController
@RequestMapping("/v1.0")
public class CustomerControllerImpl implements CustomerController {

	@Autowired
	private CustomerService customerService;

	@Override
	public ResponseEntity<String> create(Customer customer) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(customer));
		} catch (final RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<List<Customer>> getAllCustomer() {
		// TODO Auto-generated method stub
		return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomer());
	}

	@Override
	public ResponseEntity<String> deleteCustomer(String id) {
		// TODO Auto-generated method stub
		try {
			return ResponseEntity.status(HttpStatus.OK).body(customerService.deleteCustomer(id));
		}
		catch(final IllegalArgumentException E) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(E.getMessage());
		}
		catch(final RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<String> updateCustomer(String id, Customer customer) {
		// TODO Auto-generated method stub
		try {
			return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomer(id, customer));
		}
		catch(final IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch(final RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
