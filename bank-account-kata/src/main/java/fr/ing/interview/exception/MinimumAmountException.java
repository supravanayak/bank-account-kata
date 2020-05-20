package fr.ing.interview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class MinimumAmountException extends Exception{
	private static final String MESSAGE = "Deposit money from a customer to his account, is allowed when superior to €0.01 ";

    public MinimumAmountException(double amount){
        super(MESSAGE + amount);
    }

}
