package fr.ing.interview.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ing.interview.dao.TransactionDao;
import fr.ing.interview.domain.Transaction;

@Service
public class TransactionServiceImpl implements TransactionService{

	private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
	@Autowired
	private TransactionDao transactionDao ;
	
	@Override
	public void saveDepositTransaction(Transaction Transaction) {
		transactionDao.save(Transaction);		
	}

	@Override
	public void saveWithdrawTransaction(Transaction Transaction) {
		transactionDao.save(Transaction);		
	}

	@Override
	public List<Transaction> findByAccountNumber(int accountNumber) {
		 List<Transaction> transactionList = transactionDao.findByAccountNumber(accountNumber);
		return transactionList;
	}}
