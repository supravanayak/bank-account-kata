package fr.ing.interview.service;

import java.math.BigDecimal;
import java.security.Principal;

import fr.ing.interview.domain.Account;
import fr.ing.interview.domain.BalanceInfo;
import fr.ing.interview.exception.MinimumAmountException;
import fr.ing.interview.exception.ResourceNotFoundException;

public interface AccountService {
	
	Account createAccount();	
	Account findByAccountNumber (int accountNumber) throws ResourceNotFoundException;
	void deposit(Integer accountNumber, double amount) throws MinimumAmountException, ResourceNotFoundException ;
	void withdraw(Integer accountNumber, double amount) throws ResourceNotFoundException;
	BigDecimal checkAccountBalance(Integer accountNumber) throws ResourceNotFoundException;
	Account save(Account account);
	BalanceInfo checkAccountBalancedByCustomerId(Long customerId) throws ResourceNotFoundException;

}
