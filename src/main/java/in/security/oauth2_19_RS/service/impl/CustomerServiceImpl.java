package in.security.oauth2_19_RS.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
				.street(customer.getStreet()).address(customer.getAddress()).city(customer.getCity())
				.state(customer.getState()).email(customer.getEmail()).phone(customer.getPhone()).build();
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
		if (!ObjectUtils.isEmpty(customer)) {
			customerRepository.deleteById(id);
			return "Successfully deleted";
		} else if (ObjectUtils.isEmpty(customer)) {
			throw new RuntimeException("ID not found");
		} else {
			throw new IllegalArgumentException("Error Not deleted");
		}
	}

	@Override
	public String updateCustomer(Customer updateCustomer)
			throws JsonMappingException, JsonProcessingException, ParseException {

		
		if (StringUtils.isEmpty(updateCustomer.getId())) {
			throw new RuntimeException("UUID not found");
		}

		Customer customer = customerRepository.findCustomerById(updateCustomer.getId());
		JSONObject customerFromDb = (JSONObject) new JSONParser()
				.parse(new ObjectMapper().writeValueAsString(customer));

		JSONObject customerPayloadObject = (JSONObject) new JSONParser()
				.parse(new ObjectMapper().writeValueAsString(updateCustomer));

		for (Object obj : customerPayloadObject.keySet()) {
			String param = (String) obj;
			customerFromDb.put(param, customerPayloadObject.get(param));
		}

		customer = new ObjectMapper().readValue(customerFromDb.toJSONString(), Customer.class);

		customerRepository.save(customer);
		return "Successfully updated";

	}
}
