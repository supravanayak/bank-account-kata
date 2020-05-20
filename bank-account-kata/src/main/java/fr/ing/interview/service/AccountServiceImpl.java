package fr.ing.interview.service;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ing.interview.dao.AccountDao;
import fr.ing.interview.domain.Account;
import fr.ing.interview.domain.Transaction;
import fr.ing.interview.exception.MinimumAmountException;

@Service
public class AccountServiceImpl implements AccountService{

	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	private static int AccountNumber = 1301089;
	private static double DepositMoney = 0.01;
	@Autowired
	private AccountDao accountDao;

	@Autowired
	private TransactionService transactionService;
	
	@Override
	public Account save(Account account) {
		return accountDao.save(account);
	}


	@Override
	public Account createAccount() {
		Account account = new Account();
		account.setAccountBalance(new BigDecimal(0.0));
		account.setAccountNumber(accountNumberGeneration());
		accountDao.save(account);			
		return accountDao.findByAccountNumber(account.getAccountNumber());
	}

	@Override
	public void deposit(Integer accountNumber, double amount) throws MinimumAmountException {
		Account account = findByAccountNumber(accountNumber) ; 
		if(amount > DepositMoney) {
		account.setAccountBalance(account.getAccountBalance().add(new BigDecimal(amount)));
		accountDao.save(account);
		Date date = new Date();
		saveTransaction(amount, account, date);		
		}else {
			throw new MinimumAmountException(amount);
		}
	}


	private void saveTransaction(double amount, Account account, Date date) {
		Transaction transaction = new Transaction(date, "Deposit to Account","Account", "Finished", amount, account.getAccountBalance(), account.getAccountNumber());
		transactionService.saveDepositTransaction(transaction);
	}

	@Override
	public void withdraw(Integer accountNumber, double amount) {
		Account account = findByAccountNumber(accountNumber);
		account.setAccountBalance(account.getAccountBalance().subtract(new BigDecimal(amount)));
		accountDao.save(account);
		Date date = new Date();
		saveTransaction(amount, account, date);	
	}
	
	private int accountNumberGeneration() {
		return ++AccountNumber;
	}

	@Override
	public Account findByAccountNumber(int accountNumber) {		
		return accountDao.findByAccountNumber(accountNumber);
	}

	@Override
	public BigDecimal checkAccountBalance(Integer accountNumber) {
		Account account=findByAccountNumber(accountNumber);
		return account.getAccountBalance();
	}

	




}
