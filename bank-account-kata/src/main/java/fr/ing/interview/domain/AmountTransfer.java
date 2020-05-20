package fr.ing.interview.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AmountTransfer {
	private int accountNumber;
	private double ammount;
	private String transferType;	
}
