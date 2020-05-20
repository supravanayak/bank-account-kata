package fr.ing.interview.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import fr.ing.interview.dao.CustomerDao;
import fr.ing.interview.domain.Customer;
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {
	
	@Mock
	private CustomerDao customerDao;
	
	@InjectMocks
	private CustomerServiceImpl customerService;

	@Test
    public void should_Return_CustomerList_When_Present() {        
		Customer customer = new Customer(1L, "Suprava", "Suprava", "Suprava", "Nayak", "suprava.nayak@gmail.com", "6462323");
		List<Customer> expectedCustomers = Arrays.asList(customer);
        doReturn(expectedCustomers).when(customerDao).findAll();       
        List<Customer> actualCustomers = customerService.getAllCustomers();        
        assertThat(actualCustomers).isEqualTo(expectedCustomers);
    }
	
	@Test
    public void should_Return_CustomerById_If_exits()
    {
		Optional<Customer> opCustomer = Optional.ofNullable(new Customer(1L, "Suprava", "Suprava", "Suprava", "Nayak", "suprava.nayak@gmail.com", "6462323"));
		when(customerDao.findById(1L)).thenReturn(opCustomer);
		Optional<Customer> customer = customerService.findById(1L);         
        assertEquals("Suprava", customer.get().getUsername());       
        assertEquals("suprava.nayak@gmail.com", customer.get().getEmail());
    }
     
    @Test
    public void createCustomerTest()
    {
    	Customer customer = new Customer(1L, "Suprava", "Suprava", "Suprava", "Nayak", "suprava.nayak@gmail.com", "6462323");         
    	customerService.createCustomer(customer);         
        verify(customerDao, times(1)).save(customer);
    }
	
	
}
