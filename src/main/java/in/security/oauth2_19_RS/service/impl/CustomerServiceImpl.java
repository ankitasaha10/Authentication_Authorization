package in.security.oauth2_19_RS.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.w3c.dom.ranges.RangeException;

import in.security.oauth2_19_RS.entity.Customer;
import in.security.oauth2_19_RS.repository.CustomerRepository;
import in.security.oauth2_19_RS.service.CustomerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public String create(Customer customer) {

		validateCustomerCreateRequest(customer);
		Customer customerObj = Customer.builder().firstName(customer.getFirstName()).lastName(customer.getLastName())
				.address(customer.getAddress()).email(customer.getEmail()).build();
		customerRepository.save(customerObj);
		return "Successfully Created";

	}

	public void validateCustomerCreateRequest(Customer customer) {
		if (StringUtils.isEmpty(customer.getFirstName())) {
			throw new RuntimeException("First name must not be null or empty");
		}

		if (StringUtils.isEmpty(customer.getLastName())) {
			throw new RuntimeException("Last name must not be null or empty");
		}
	}

	@Override
	public List<Customer> getAllCustomer() {
		List<Customer> list = new ArrayList<>();
		for (Customer customer : customerRepository.findAll()) {
			list.add(customer);
		}
		return list;
	}

	@Override
	public String deleteCustomer(String id) {
		
		Customer customer = customerRepository.findCustomerById(id);
		if(ObjectUtils.isEmpty(customer)){
			throw new RuntimeException("ID not found");
		}
		
		if(!ObjectUtils.isEmpty(customer)) {
			throw new IllegalArgumentException("Error Not deleted");
		}
		else 
		{
		customerRepository.deleteById(id);
		

		return "Successfully deleted";
		}
		
	}

	@Override
	public String updateCustomer(String id, Customer customer) {

		if (ObjectUtils.isEmpty(customer)) {
            throw new IllegalArgumentException("Body is empty");
        }

        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("Id is empty");
        }

        Customer existingCustomer = customerRepository.findCustomerById(id);

        if (ObjectUtils.isEmpty(existingCustomer)) {
            throw new RuntimeException("UUID not found");
        }

        existingCustomer.setCity(customer.getCity());
        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setEmail(customer.getEmail());

        // Save the updated customer
        customerRepository.save(existingCustomer);
        return "Successfully updated";
	}

}
