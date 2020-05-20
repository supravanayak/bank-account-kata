package fr.ing.interview.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ing.interview.domain.Transaction;
import fr.ing.interview.exception.ResourceNotFoundException;
import fr.ing.interview.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/displayAccountTransaction/{accountNumber}")
	public ResponseEntity<List<Transaction>> displayAccountTransaction(@PathVariable(value = "accountNumber") Integer accountNumber) throws ResourceNotFoundException{
		List<Transaction> transactioList =transactionService.findByAccountNumber(accountNumber);
		logger.info("List of Transaction as per accountNumber:"+ transactioList);
		return ResponseEntity.ok().body(transactioList);		
	}

}
