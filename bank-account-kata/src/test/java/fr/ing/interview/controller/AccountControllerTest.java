package fr.ing.interview.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.ing.interview.domain.Account;
import fr.ing.interview.service.AccountService;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {
	//private static final Logger logger = LoggerFactory.getLogger(CustomerControllerTest.class);

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	AccountService accountService;

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	public void  GetAccountURLCheckTest() throws Exception{

		mockMvc.perform(MockMvcRequestBuilders.get("/account"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string("Account URL is ok"));					
	}

	@Test
	public void createAccountTest() throws Exception{
		Account account = new Account(1L,007111333,new BigDecimal(100),1L);
		String jsonRequest = mapper.writeValueAsString(account);
		mockMvc.perform(MockMvcRequestBuilders.post("/account/createAccount").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
	}

	/*
	 * @Test public void moneyDeposittest() throws Exception { mockMvc.perform(
	 * MockMvcRequestBuilders
	 * .post("/account/deposit/amount=100&accountNumber=007111333")
	 * .contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
	 * .andExpect(status().isCreated()); }
	 * 
	 * @Test public void moneyWithdrawtest() throws Exception { mockMvc.perform(
	 * MockMvcRequestBuilders
	 * .post("/account/withdraw/amount=100&accountNumber=007111333")
	 * .contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
	 * .andExpect(status().isCreated()); }
	 */
	@Test
	public void should_GetBalance_When_ValidRequest() throws Exception{
		Account account = new Account(1L,007111333,new BigDecimal(100),1L);		 
		when(accountService.findByAccountNumber(1)).thenReturn(account);
		mockMvc.perform(get("/account/checkAccountBalance/1") 
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.accountNumber").value(007111333))
		.andExpect(jsonPath("$.accountBalance").value(new BigDecimal(100)));
		
	}

	/*
	 * @Test public void should_Return_404_When_NotValidRequest() throws Exception{
	 * mockMvc.perform(MockMvcRequestBuilders.get("/account/checkAccountBalance/1")
	 * .accept(MediaType.APPLICATION_JSON)) .andDo(print())
	 * .andExpect(status().isNotFound()); }
	 */
}
