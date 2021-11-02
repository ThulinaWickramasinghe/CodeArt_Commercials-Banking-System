package com.codeartcommercials.exceptions;

@SuppressWarnings("serial")
public class InvalidPasswordFormatException extends Exception {
	public InvalidPasswordFormatException() {
		super("Password should be 8 characters long");
	}
}
