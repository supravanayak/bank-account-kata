package fr.ing.interview.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ing.interview.dao.TransactionDao;
import fr.ing.interview.domain.Account;
import fr.ing.interview.domain.Transaction;
import fr.ing.interview.exception.MinimumAmountException;
import fr.ing.interview.exception.ResourceNotFoundException;

@Service
public class TransactionServiceImpl implements TransactionService{

	private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
	@Autowired
	private TransactionDao transactionDao ;

	@Autowired
	private AccountServiceImpl accountService;

	@Override
	public void saveDepositTransaction(Transaction Transaction) {
		logger.info("Deposit Transaction Started :", Transaction);
		transactionDao.save(Transaction);		
	}

	@Override
	public void saveWithdrawTransaction(Transaction Transaction) {
		logger.info("Withdraw Transaction Started :", Transaction);
		transactionDao.save(Transaction);		
	}

	@Override
	public List<Transaction> findTransactionByAccountNumber(int accountNumber) throws ResourceNotFoundException{
		logger.info("Retrieve Transaction details for:"+accountNumber);
		List<Transaction> transactionList = Optional.of(transactionDao.findByAccountNumber(accountNumber))
				.orElseThrow(() -> new ResourceNotFoundException((long) accountNumber));
		if(transactionList.isEmpty()) { throw new ResourceNotFoundException((long)
				accountNumber); }

		return transactionList;
	}

	@Override
	public List<Transaction> findTransactionByCustomerId(Long customerId) throws ResourceNotFoundException {
		List<Account> accounts=accountService.findAccountNumberByCustomerId(customerId);
		accounts.stream().map(c->c.getAccountNumber()).collect(Collectors.toList());
		List<Transaction> transactionList = transactionDao.findByAccountNumberlist(accounts.stream().map(c->c.getAccountNumber()).collect(Collectors.toList()));
		if(transactionList.isEmpty()) {
			throw new ResourceNotFoundException(customerId);
		}
		return transactionList;
	}}
