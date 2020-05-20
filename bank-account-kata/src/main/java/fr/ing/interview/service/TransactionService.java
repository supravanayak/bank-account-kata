package fr.ing.interview.service;

import java.util.List;

import fr.ing.interview.domain.Transaction;

public interface TransactionService {
	
	List<Transaction> findByAccountNumber(int accountNumber);
	void saveDepositTransaction(Transaction Transaction);
	void saveWithdrawTransaction(Transaction Transaction);

	
}
