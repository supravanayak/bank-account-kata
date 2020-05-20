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
    public Customer createCustomer(@RequestBody Customer Customer) {
        return customerService.createCustomer(Customer);
    }

    @GetMapping("/getAllCustomers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/getCustomers/{id}")
    public ResponseEntity < Customer > getCustomerById(@PathVariable(value = "id") Long CustomerId)
    throws ResourceNotFoundException {
        Customer customer = customerService.findById(CustomerId)
            .orElseThrow(() -> new ResourceNotFoundException(CustomerId));        
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
        return ResponseEntity.ok(updatedCustomer);
    }
    
    

    @DeleteMapping("/Customers/{id}")
    public Map < String, Boolean > deleteCustomer(@PathVariable(value = "id") Long CustomerId)
    throws ResourceNotFoundException {
        Customer Customer = customerService.findById(CustomerId)
            .orElseThrow(() -> new ResourceNotFoundException(CustomerId));

        customerService.delete(Customer);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    
}


}
