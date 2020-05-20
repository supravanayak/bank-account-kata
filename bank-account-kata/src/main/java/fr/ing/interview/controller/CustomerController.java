package fr.ing.interview.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ing.interview.domain.Customer;
import fr.ing.interview.exception.ResourceNotFoundException;
import fr.ing.interview.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;
    
    @GetMapping
	public String getCustomerUrlCheck() {
    	return "Customer URL is ok";
	}
    
    @PostMapping("/createCustomers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer customers = customerService.createCustomer(customer);
        logger.info("Customer Created Sucessfully", customer);
        return ResponseEntity.ok().body(customers);
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customerList = customerService.getAllCustomers();
        logger.info("Retrieved Sucessfully list of customer"+customerList);
        return ResponseEntity.ok().body(customerList);
    }

    @GetMapping("/getCustomers/{id}")
    public ResponseEntity < Customer > getCustomerById(@PathVariable(value = "id") Long customerId)
    throws ResourceNotFoundException {
        Customer customer = customerService.findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException(customerId)); 
        logger.info("Retrieved customer details for id"+customerId+":"+customer);
        return ResponseEntity.ok().body(customer);
    }   

    @PutMapping("/updateCustomers/{id}")
    public ResponseEntity < Customer > updateCustomer(@PathVariable(value = "id") Long CustomerId,
        @RequestBody Customer CustomerDetails) throws ResourceNotFoundException {
        Customer customer = customerService.findById(CustomerId)
            .orElseThrow(() -> new ResourceNotFoundException(CustomerId));

        customer.setEmail(CustomerDetails.getEmail());
        customer.setLastName(CustomerDetails.getLastName());
        customer.setFirstName(CustomerDetails.getFirstName());
        final Customer updatedCustomer = customerService.UpdateCusomter(customer);
        logger.info("Customer updated :"+CustomerId);
        return ResponseEntity.ok(updatedCustomer);
    }
    
    

    @DeleteMapping("/Customers/{id}")
    public Map < String, Boolean > deleteCustomer(@PathVariable(value = "id") Long customerId)
    throws ResourceNotFoundException {
        Customer Customer = customerService.findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException(customerId));

        customerService.delete(Customer);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        logger.warn("Customer deleted for customerId :"+customerId);
        return response;
    
}


}
