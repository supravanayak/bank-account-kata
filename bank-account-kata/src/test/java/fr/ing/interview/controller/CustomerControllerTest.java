package fr.ing.interview.controller;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.ing.interview.domain.Customer;
import fr.ing.interview.service.CustomerService;
import fr.ing.interview.service.CustomerServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerControllerTest.class);
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomerService customerService;
	
	private final ObjectMapper mapper = new ObjectMapper();
	

	@Test
	public void createCustomerApiTest() throws Exception, JsonProcessingException, Exception {
		Customer customer = new Customer(1L, "Suprava", "Suprava", "Suprava", "Nayak", "suprava.nayak@gmail.com", "6462323");	
		  String res = mockMvc.perform(post("/customer/createCustomers")
		  .content(mapper.writeValueAsString(customer))
		  .contentType(MediaType.APPLICATION_JSON)) .andExpect(status().isOk())
		  .andReturn() .getResponse() .getContentAsString();
		  logger.info("res :  "+res); assertTrue(res, Boolean.TRUE);
		 
	//	verify(customerService, times(1)).delete(customer);
		/*
		 * given(customerService.createCustomer((Customer)any(Customer.class))).
		 * willReturn(customer); mockMvc.perform( MockMvcRequestBuilders
		 * .post("/customer/createCustomers")
		 * .content(mapper.writeValueAsString(customer))
		 * .contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
		 * .andExpect(status().isCreated())
		 * .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").exists());
		 * 
		 */	}
	
	@Test
	public void should_GetCustomerById_When_ValidRequest() throws Exception {		
	  Customer customer = new Customer(1L, "Suprava", "Suprava", "Suprava", "Nayak", "suprava.nayak@gmail.com", "6462323");
	  Optional<Customer> opcustomer = Optional.ofNullable(customer);
	  when(customerService.findById(1L)).thenReturn(opcustomer);
	  mockMvc.perform(get("/customer/getCustomers/1") 
	           .accept(MediaType.APPLICATION_JSON))
	           .andExpect(status().isOk())
	           .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
	           .andExpect(jsonPath("$.customerId").value(1L))
	           .andExpect(jsonPath("$.email").value("suprava.nayak@gmail.com"));
	           
	}
			 
	@Test
	public void should_Return404_When_CustomerNotFound()throws Exception 
	{
		mockMvc.perform( MockMvcRequestBuilders
	      .get("/customer/getCustomers/1")
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isNotFound());	     
	}
	
	
	@Test 
	public void findAllCustomerTest() throws Exception{
		
		Customer customer = new Customer(1L, "Suprava", "Suprava", "Suprava", "Nayak", "suprava.nayak@gmail.com", "6462323");
		List<Customer> customerList = new ArrayList<>();
		customerList.add(customer);
		given(customerService.getAllCustomers()).willReturn(customerList);		
		mockMvc.perform(get("/customer/getAllCustomers"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].username", is("Suprava")));
		
	}
			
}
