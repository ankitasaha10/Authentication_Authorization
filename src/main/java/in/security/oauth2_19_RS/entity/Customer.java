package in.security.oauth2_19_RS.entity;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import in.security.oauth2_19_RS.model.ICustomerCreateRequest;
import in.security.oauth2_19_RS.model.ICustomerUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "customer_details")
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty(groups = { ICustomerUpdateRequest.class }, message = "Id must not be null or empty")
	@Id
	private String id;

	@NotEmpty(groups = { ICustomerCreateRequest.class }, message = "First name must not be null or empty")
	private String firstName;

	@NotEmpty(groups = { ICustomerCreateRequest.class }, message = "Last name must not be null or empty")
	private String lastName;

	private String street;
	private String address;
	private String city;
	private String state;
	private String email;
	private String phone;

}
