package fr.ing.interview.service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ing.interview.controller.CustomerController;
import fr.ing.interview.dao.AccountDao;
import fr.ing.interview.domain.Account;
import fr.ing.interview.domain.Customer;
import fr.ing.interview.domain.Transaction;

@Service
public class AccountServiceImpl implements AccountService{

	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	private static int AccountNumber = 1301089;
	private static double DepositMoney = 0.01;
	@Autowired
	private AccountDao accountDao;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private TransactionService transactionService;

	@Override
	public Account createAccount() {
		Account account = new Account();
		account.setAccountBalance(new BigDecimal(0.0));
		account.setAccountNumber(accountNumberGeneration());
		accountDao.save(account);			
		return accountDao.findByAccountNumber(account.getAccountNumber());
	}

	@Override
	public void deposit(Integer accountNumber, double amount) {
		Account account = findByAccountNumber(accountNumber) ; 
		if(amount > DepositMoney) {
		account.setAccountBalance(account.getAccountBalance().add(new BigDecimal(amount)));
		accountDao.save(account);
		Date date = new Date();
		Transaction transaction = new Transaction(date, "Deposit to Account","Account", "Finished", amount, account.getAccountBalance(), account.getAccountNumber());
		transactionService.saveDepositTransaction(transaction);
		}

	}

	@Override
	public void withdraw(Integer accountNumber, double amount) {
		Account account = findByAccountNumber(accountNumber);
		account.setAccountBalance(account.getAccountBalance().subtract(new BigDecimal(amount)));
		accountDao.save(account);
		Date date = new Date();
		Transaction transaction = new Transaction(date, "Withdraw from Account","Account", "Finished", amount, account.getAccountBalance(), account.getAccountNumber());
		transactionService.saveWithdrawTransaction(transaction);
	}
	
	private int accountNumberGeneration() {
		return ++AccountNumber;
	}

	@Override
	public Account findByAccountNumber(int accountNumber) {		
		return accountDao.findByAccountNumber(accountNumber);
	}

	@Override
	public void deposit(String accountType, double amount, Principal principal) {
		// TODO Auto-generated method stub

	}

	@Override
	public void withdraw(String accountType, double amount, Principal principal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BigDecimal checkAccountBalance(Integer accountNumber) {
		Account account=findByAccountNumber(accountNumber);
		return account.getAccountBalance();
	}





}
