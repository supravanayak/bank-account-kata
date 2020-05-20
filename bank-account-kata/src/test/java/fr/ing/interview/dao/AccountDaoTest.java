package fr.ing.interview.dao;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import fr.ing.interview.domain.Account;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountDaoTest {

	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private AccountDao accountDao;
 
	@Test
	public void whenfindByAccountNumber_thenReturnAccount() {
	    
		/*
		 * Account acc = new Account(1L, 64131, new BigDecimal(100), 1L,"saving");
		 * entityManager.persist(acc); entityManager.flush(); Account found =
		 * accountDao.findByAccountNumber(acc.getAccountNumber());
		 * assertEquals(found.getAccountBalance(), acc.getAccountNumber());
		 */  
	}
}
