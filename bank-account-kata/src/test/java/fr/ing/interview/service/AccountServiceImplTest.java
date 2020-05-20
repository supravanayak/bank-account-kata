package fr.ing.interview.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import fr.ing.interview.dao.AccountDao;
import fr.ing.interview.domain.Account;
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {

	@Mock
	AccountDao accountDao;

	@InjectMocks
	AccountServiceImpl accountService;

	@Test
	public void createAccounttest() {
		Account account = new Account(1L, 7111333, new BigDecimal(10), 1L);
		accountService.save(account);         
		verify(accountDao, times(1)).save(account);
	}

	@Test
	public void should_Deposite_Amount_Return_AccountBalance() {
		
	}

	@Test
	public void should_Withdraw_Amount_Return_AccountBalance() {

	}
	
}
