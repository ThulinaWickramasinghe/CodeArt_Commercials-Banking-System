package com.codeartcommercials.exceptions;

@SuppressWarnings("serial")
public class InvalidBalanceException extends Exception {
	public InvalidBalanceException() {
		super("Account should have a minimum balance of Rs.1500.00");
	}
}
