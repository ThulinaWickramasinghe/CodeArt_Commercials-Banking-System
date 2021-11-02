package com.codeartcommercials.exceptions;

@SuppressWarnings("serial")
public class ImpossibleWithdrawAmountException extends Exception {
	public ImpossibleWithdrawAmountException() {
		super("Withdraw amount exceeds account balance");
	}
	
}
