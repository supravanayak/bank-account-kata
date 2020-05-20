package fr.ing.interview.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.ing.interview.domain.Customer;

@Repository
public interface CustomerDao extends CrudRepository<Customer, Long>{
	}
