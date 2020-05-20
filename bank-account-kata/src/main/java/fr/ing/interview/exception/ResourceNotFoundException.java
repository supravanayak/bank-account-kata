package fr.ing.interview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException  extends Exception{
	private static final String MESSAGE = "Customer not found for this id ";

	    public ResourceNotFoundException(Long customerId){
	        super(MESSAGE + customerId);
	    }
}
