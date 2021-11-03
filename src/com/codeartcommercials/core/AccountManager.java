package com.codeartcommercials.core;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.codeartcommercials.exceptions.InvalidBalanceException;
import com.codeartcommercials.exceptions.InvalidCurrencyException;
import com.codeartcommercials.exceptions.InvalidPasswordException;
import com.codeartcommercials.exceptions.InvalidPasswordFormatException;

public class AccountManager {//singleton design pattern - early instantiation used here to create a single account manager object
	private HashMap<String,Account> accounts = new HashMap<String,Account>();
	private static AccountManager mgr = new AccountManager();//static member
	private File accDetails;
	
	private AccountManager() {//private constructor
		this.accDetails = new File("accDetails.txt");
		try {
			if(accDetails.createNewFile()) {
				System.out.println("New file created succesfully");
			}else {
				System.out.println("File already exists");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static AccountManager getAccMgr() {//static factory method
		return mgr;
	}
	
	public Account createNewAccount(String ID, String type, Customer owner, int balance, String branch, String pin) throws InvalidCurrencyException, InvalidBalanceException, InvalidPasswordFormatException {
		accounts.put(ID,new Account(ID,type,owner,balance,branch,pin));
		return accounts.get(ID);
	}
	
	public Account getAccountByOwner(String ID,String pin) throws InvalidPasswordException {
		if(accounts.get(ID).getPassword().equals(pin)) {
			return accounts.get(ID);
		}else {
			throw new InvalidPasswordException();
		}
	}
	
	public Account getAccount(String ID) {
		return accounts.get(ID);
	}
	
	public boolean closeAccount(String ID , String pin) throws InvalidPasswordException {
		if(true == accounts.get(ID).getPassword().equals(pin)) {
			if(null != accounts.remove(ID)) {
				return true;
			}
		}else {
			throw new InvalidPasswordException();
		}
		
		return false;
	}
}
