package fr.ing.interview.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import fr.ing.interview.dao.TransactionDao;
import fr.ing.interview.domain.Transaction;
import fr.ing.interview.exception.ResourceNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {
	
	@Mock
	private TransactionDao transactionDao;

	@InjectMocks
	private TransactionServiceImpl transactionService;
	
	@Test
	public void saveDepositTransactiontest() {
		Date date = new Date();
		Transaction transaction = new Transaction(1L,date, "Deposit to Account", "Account", "Finished", 100.0, new BigDecimal(1000), 7111333);         
		transactionService.saveDepositTransaction(transaction);;         
        verify(transactionDao, times(1)).save(transaction);
	}
	
	@Test
	public void saveWithdrawTransaction() {
		Date date = new Date();
		Transaction transaction = new Transaction(1L,date, "withdraw to Account", "Account", "Finished", 100.0, new BigDecimal(1000), 7111333);
		transactionService.saveWithdrawTransaction(transaction);         
        verify(transactionDao, times(1)).save(transaction);
	}
	
	@Test
	public void should_Return_ListOfTransaction_WhenAccountNumberExits() throws ResourceNotFoundException {
		Date date = new Date();
		Transaction transaction = new Transaction(1L,date, "Deposit to Account", "Account", "Finished", 100.0, new BigDecimal(1000), 7111333);		
		List<Transaction> expectedTrannsaction = Arrays.asList(transaction);
		when(transactionDao.findByAccountNumber(7111333)).thenReturn(expectedTrannsaction);               
        List<Transaction> actualTransaction = transactionService.findTransactionByAccountNumber(7111333);        
        assertThat(actualTransaction).isEqualTo(expectedTrannsaction);
	}	

}
