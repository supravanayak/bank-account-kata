package fr.ing.interview.service;

import java.math.BigDecimal;
import java.security.Principal;

import fr.ing.interview.domain.Account;
import fr.ing.interview.exception.MinimumAmountException;

public interface AccountService {
	
	Account createAccount();	
	Account findByAccountNumber (int accountNumber);
	void deposit(Integer accountNumber, double amount) throws MinimumAmountException ;
	void withdraw(Integer accountNumber, double amount);
	BigDecimal checkAccountBalance(Integer accountNumber);
	Account save(Account account);

}
