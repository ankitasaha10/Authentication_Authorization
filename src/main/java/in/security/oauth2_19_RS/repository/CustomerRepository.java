package in.security.oauth2_19_RS.repository;






import org.springframework.data.mongodb.repository.MongoRepository;

import in.security.oauth2_19_RS.entity.Customer;



public interface CustomerRepository extends MongoRepository<Customer, String> {

	Customer findCustomerById(String id);

	
}

