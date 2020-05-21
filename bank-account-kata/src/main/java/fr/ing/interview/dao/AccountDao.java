package fr.ing.interview.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.ing.interview.domain.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Long>{

	@Query("from Account where accountNumber = ?1")
	Account findByAccountNumber(int accountNumber);
	
	@Query("from Account where customerId = ?1")
	List<Account> findByCustomerId(Long customerId);
}
