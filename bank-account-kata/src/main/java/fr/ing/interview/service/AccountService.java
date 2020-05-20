package fr.ing.interview.service;

import java.math.BigDecimal;
import java.security.Principal;

import fr.ing.interview.domain.Account;

public interface AccountService {
	
	Account createAccount();	
	Account findByAccountNumber (int accountNumber);
	void deposit(String accountType, double amount, Principal principal);
	void withdraw(String accountType, double amount, Principal principal);

	void deposit(Integer accountNumber, double amount) ;
	void withdraw(Integer accountNumber, double amount);
	BigDecimal checkAccountBalance(Integer accountNumber);

}
