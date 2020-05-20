package fr.ing.interview.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ing.interview.BankAccountKataApplication;
import fr.ing.interview.exception.ResourceNotFoundException;
import fr.ing.interview.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountService accountService;	
	
	@GetMapping
		public String getAccountUrlCheck() {
			return "Account URL is ok";
		}

	@PostMapping("/deposit")
	public ResponseEntity<String> deposit(@Param("amount") String amount,
			@Param("accountNumber") Integer accountNumber) throws ResourceNotFoundException {
		accountService.deposit(accountNumber, Double.parseDouble(amount));
		return new ResponseEntity<String>(HttpStatus.OK) ;
	}

	@PostMapping("/withdraw")
	public ResponseEntity<String> withdraw(@Param("amount") String amount,
			@Param("accountNumber") Integer accountNumber) throws ResourceNotFoundException{
		accountService.withdraw(accountNumber, Double.parseDouble(amount));

		return new ResponseEntity<String>(HttpStatus.OK) ;
	}

	@GetMapping("/checkAccountBalance/{accountNumber}")
	public BigDecimal checkAccountBalance(@PathVariable(value = "accountNumber") Integer accountNumber) throws ResourceNotFoundException{
		return accountService.checkAccountBalance(accountNumber);		
	}

	
	/*
	 * @ExceptionHandler({MinimumAmountException.class }) public void
	 * handleMinimumAmountTrasferException() { errrocode:ERR_MINIMUM errormsg: }
	 */

}
