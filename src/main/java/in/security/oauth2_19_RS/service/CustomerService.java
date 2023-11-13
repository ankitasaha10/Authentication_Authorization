package in.security.oauth2_19_RS.service;

import java.util.List;

import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import in.security.oauth2_19_RS.entity.Customer;

public interface CustomerService {

	public String create(Customer customer);

	public List<Customer> getAllCustomer();

	public String deleteCustomer(String id);

	public String updateCustomer(Customer customer)
			throws JsonMappingException, JsonProcessingException, ParseException;

}
