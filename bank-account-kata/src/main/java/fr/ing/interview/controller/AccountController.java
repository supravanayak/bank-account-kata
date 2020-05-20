package fr.ing.interview.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ing.interview.domain.Account;
import fr.ing.interview.domain.AmountTransfer;
import fr.ing.interview.domain.BalanceInfo;
import fr.ing.interview.domain.Receipt;
import fr.ing.interview.exception.MinimumAmountException;
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
	
	@PostMapping("/createAccount")
	public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account){
		Account accounts =accountService.save(account);
		logger.info("Account Created Sucessfully", accounts);
		return ResponseEntity.ok().body(accounts);
	}

	@PostMapping("/deposit")
	public ResponseEntity<Receipt> depositAmount(@Valid @RequestBody AmountTransfer amountTransfer) throws MinimumAmountException, ResourceNotFoundException{
		accountService.deposit(amountTransfer.getAccountNumber(), amountTransfer.getAmmount());
		logger.info("Customer Deposited to accountNumber "+amountTransfer.getAccountNumber()+"with amount "+amountTransfer.getAmmount());
		return ResponseEntity.ok().body(new Receipt("Deposited Amount Successfully:"+amountTransfer.getAmmount(), Boolean.TRUE));
		
	}
	
	@PostMapping("/withdraw")
	public ResponseEntity<Receipt> withdrawAmount(@Valid @RequestBody AmountTransfer amountTransfer) throws MinimumAmountException, ResourceNotFoundException{
		accountService.withdraw(amountTransfer.getAccountNumber(), amountTransfer.getAmmount());
		logger.info("Customer withdraw from accountNumber "+amountTransfer.getAccountNumber()+"of amount "+amountTransfer.getAmmount());
		return ResponseEntity.ok().body(new Receipt("Amount withdraw Successfully:"+amountTransfer.getAmmount(), Boolean.TRUE));
		
	}
	
	@GetMapping("/checkAccountBalance/{accountNumber}")
	public ResponseEntity<BalanceInfo> checkAccountBalance(@PathVariable(value = "accountNumber") Integer accountNumber) throws ResourceNotFoundException{
		Account account = accountService.findByAccountNumber(accountNumber);	
		logger.info("Customer Total Account Balance :"+account.getAccountBalance());
		return ResponseEntity.ok().body(new BalanceInfo(account.getAccountNumber(), account.getAccountBalance()));	
	}

}
