package fr.ing.interview.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.ing.interview.domain.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Long>{

	@Query("from Account where accountNumber = ?1")
	Account findByAccountNumber(int accountNumber);
}
