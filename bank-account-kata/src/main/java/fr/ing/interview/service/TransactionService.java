package fr.ing.interview.service;

import java.util.List;

import fr.ing.interview.domain.Transaction;
import fr.ing.interview.exception.ResourceNotFoundException;

public interface TransactionService {
	
	List<Transaction> findByAccountNumber(int accountNumber) throws ResourceNotFoundException;
	void saveDepositTransaction(Transaction Transaction);
	void saveWithdrawTransaction(Transaction Transaction);

	
}
