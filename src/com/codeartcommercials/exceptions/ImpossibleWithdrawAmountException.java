package com.codeartcommercials.exceptions;

public class ImpossibleWithdrawAmountException extends Exception {
	public ImpossibleWithdrawAmountException() {
		super("Withdraw amount exceeds account balance");
	}
	
}
