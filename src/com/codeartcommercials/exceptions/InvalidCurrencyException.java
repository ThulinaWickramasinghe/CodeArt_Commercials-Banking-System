/**
 * 
 */
package com.codeartcommercials.exceptions;

/**
 * @author it20654580
 *
 */
@SuppressWarnings("serial")
public class InvalidCurrencyException extends Exception {
	public InvalidCurrencyException(){
		super("Currency cannot be negative");
	}
}
