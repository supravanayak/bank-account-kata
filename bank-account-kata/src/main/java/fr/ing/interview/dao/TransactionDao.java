package fr.ing.interview.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.ing.interview.domain.Transaction;

@Repository
public interface TransactionDao extends CrudRepository<Transaction, Long>{
	
	@Query("from Transaction where accountNumber = ?1")
	List<Transaction> findByAccountNumber(int accountNumber);

}
