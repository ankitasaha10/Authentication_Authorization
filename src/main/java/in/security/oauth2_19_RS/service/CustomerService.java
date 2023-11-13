package in.security.oauth2_19_RS.service;

import java.util.List;

import in.security.oauth2_19_RS.entity.Customer;

public interface CustomerService {
	
	public String create(Customer customer);
	
	public List<Customer> getAllCustomer();
	
	public String deleteCustomer(String id);
	
	public String updateCustomer(String id, Customer customer);

}
