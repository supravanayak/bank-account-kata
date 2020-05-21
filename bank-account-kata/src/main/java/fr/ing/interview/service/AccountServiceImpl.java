package fr.ing.interview.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ing.interview.dao.AccountDao;
import fr.ing.interview.domain.Account;
import fr.ing.interview.domain.BalanceInfo;
import fr.ing.interview.domain.Transaction;
import fr.ing.interview.exception.MinimumAmountException;
import fr.ing.interview.exception.ResourceNotFoundException;

@Service
public class AccountServiceImpl implements AccountService{

	private static final String DEPOSIT_TO_ACCOUNT = "Deposit to Account";
	private static final String WITHDRAW_FROM_ACCOUNT = "Withdraw from Account";
	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	private static int AccountNumber = 1301089;
	private static double DepositMoney = 0.01;
	@Autowired
	private AccountDao accountDao;

	@Autowired
	private TransactionService transactionService;

	@Override
	public Account save(Account account) {
		account.setAccountNumber(accountNumberGeneration());
		return accountDao.save(account);
	}

	@Override
	public Account createAccount() {
		Account account = new Account();
		account.setAccountBalance(new BigDecimal(0.0));
		account.setAccountNumber(accountNumberGeneration());
		accountDao.save(account);
		logger.info("AccountCreate"+account);
		return accountDao.findByAccountNumber(account.getAccountNumber());
	}

	@Override
	public void deposit(Integer accountNumber, double amount) throws MinimumAmountException, ResourceNotFoundException {
		Account account = findByAccountNumber(accountNumber) ; 
		if(amount > DepositMoney) {
			account.setAccountBalance(account.getAccountBalance().add(new BigDecimal(amount)));
			accountDao.save(account);
			logger.info("Amount Deposited :"+amount);
			Date date = new Date();			
			saveTransaction(amount,account,date,DEPOSIT_TO_ACCOUNT);	
			logger.info("Transaction Successful");
		}else {
			logger.error("Deposit money from a customer to his account, is allowed when superior to €0.01"+amount);
			throw new MinimumAmountException(amount);

		}
	}


	private void saveTransaction(double amount, Account account, Date date,String description) {
		Transaction transaction = new Transaction(date, description,account.getAccountType(), "Finished", amount, account.getAccountBalance(), account.getAccountNumber());
		transactionService.saveDepositTransaction(transaction);
	}

	@Override
	public void withdraw(Integer accountNumber, double amount) throws ResourceNotFoundException {
		Account account = findByAccountNumber(accountNumber);
		account.setAccountBalance(account.getAccountBalance().subtract(new BigDecimal(amount)));
		accountDao.save(account);
		logger.info("Amount withdraw completed :"+amount);
		Date date = new Date();		
		saveTransaction(amount, account, date,WITHDRAW_FROM_ACCOUNT);	
		logger.info("Transaction Successful");
	}

	private int accountNumberGeneration() {
		return ++AccountNumber;
	}

	@Override
	public Account findByAccountNumber(int accountNumber) throws ResourceNotFoundException{		
		Account accountOptional = Optional.ofNullable(accountDao.findByAccountNumber(accountNumber))
				.orElseThrow(() -> new ResourceNotFoundException((long) (accountNumber)));
		Optional.of(accountOptional).ifPresent(acc -> {
			logger.info("Account Number Exist = " + accountOptional.getAccountNumber());   
		});

		return accountOptional;
	}

	@Override
	public BigDecimal checkAccountBalance(Integer accountNumber) throws ResourceNotFoundException {
		Account account=findByAccountNumber(accountNumber);
		return account.getAccountBalance();
	}

	@Override
	public BalanceInfo checkAccountBalancedByCustomerId(Long customerId) throws ResourceNotFoundException{
		List<Account> accountList = findAccountNumberByCustomerId(customerId);
		if(accountList.isEmpty()) {
			throw new ResourceNotFoundException(customerId);
		}
		double balance = accountList.stream().mapToDouble(c -> c.getAccountBalance().longValue()).sum();	
		logger.info("Account Balance for:"+customerId+" "+balance);		
		return new BalanceInfo(new BigDecimal(balance),customerId);
	}

	public List<Account> findAccountNumberByCustomerId(Long customerId) {
		Optional<List<Account>> accountList=Optional.of(accountDao.findByCustomerId(customerId));
		return accountList.get();
	}

}
