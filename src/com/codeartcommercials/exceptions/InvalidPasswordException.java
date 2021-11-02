package com.codeartcommercials.exceptions;

@SuppressWarnings("serial")

public class InvalidPasswordException extends Exception {
	public InvalidPasswordException() {
		super("Invalid password");
	}
}
