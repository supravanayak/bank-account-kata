package fr.ing.interview.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ing.interview.dao.CustomerDao;
import fr.ing.interview.domain.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{	

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Autowired
	CustomerDao customerDao;

	@Override
	public List<Customer> getAllCustomers() {
		return (List<Customer>) customerDao.findAll();
	}

	@Override
	public Customer createCustomer(Customer customer) {
		return customerDao.save(customer);
	}

	@Override
	public Optional<Customer> findById(Long customerId) {
		return customerDao.findById(customerId);
	}


	@Override
	public void delete(Customer customer) {		
		customerDao.delete(customer);
	}

	@Override
	public Customer UpdateCusomter(Customer customer) {
		return customerDao.save(customer);
	}

}
