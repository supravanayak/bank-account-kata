package fr.ing.interview.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BalanceInfo {
	private int accountNumber;
	private BigDecimal accountBalance;
	private long customerId;
	public BalanceInfo(int accountNumber, BigDecimal accountBalance) {
		super();
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
	}
	public BalanceInfo(BigDecimal accountBalance, long customerId) {
		super();
		this.accountBalance = accountBalance;
		this.customerId = customerId;
	}
	
}
