package fr.ing.interview.service;

import java.util.List;
import java.util.Optional;

import fr.ing.interview.domain.Customer;

public interface CustomerService {

	List<Customer> getAllCustomers();

	Customer createCustomer(Customer customer);

	Optional<Customer> findById(Long customerId);	

	void delete(Customer customer);

	Customer UpdateCusomter(Customer customer);

	Customer findByUsername(String username);
	Customer findByEmail(String email);
	
	boolean checkCustomerExists(String username, String email);
	boolean checkUsernameExists(String username);
	boolean checkEmailExists(String email);

}
