package com.codeartcommercials.service;

import com.codeartcommercials.exceptions.ImpossibleWithdrawAmountException;
import com.codeartcommercials.exceptions.InvalidBalanceException;
import com.codeartcommercials.exceptions.InvalidCurrencyException;

class Account {
	
	private String type;
	private Customer owner;
	private int balance; //balance will be stored using integer data type and measured using cents(Sri Lankan) because of issues of floating point data type with currency
	private String branch;
	private TransactionLog transactions;
	private String ID;
	
	public Account(String ID, String type, Customer owner, int amount, String branch) throws InvalidCurrencyException, InvalidBalanceException {
		super();
		setID(ID);
		setType(type);
		setOwner(owner);
		deposit(amount);
		setBranch(branch);
		transactions = new TransactionLog(this.ID);
	}
	
	public void withdraw(int amount) throws InvalidCurrencyException, InvalidBalanceException, ImpossibleWithdrawAmountException{
		if(amount > getBalance()) {
			throw new ImpossibleWithdrawAmountException();
		}else {
			setBalance(getBalance()-amount);
			amount=-amount;
			transactions.write(amount, getBalance());//write to the transaction log
		}
	}
	
	public void deposit(int amount) throws InvalidCurrencyException, InvalidBalanceException {
		if(amount < 0) {
			throw new InvalidCurrencyException();
		}else {
			setBalance(getBalance()+amount);
			transactions.write(amount, getBalance());//write to the transaction log
		}
		
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String iD) {
		ID = iD;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Customer getOwner() {
		return owner;
	}
	
	public void setOwner(Customer owner) {
		this.owner = owner;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) throws InvalidCurrencyException, InvalidBalanceException {
		if(balance < 0) {
			throw new InvalidCurrencyException();
		}else if(balance < 150000){
			throw new InvalidBalanceException();
		}else {
			this.balance = balance;
		}
	}
	
	public String getBranch() {
		return branch;
	}
	
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	public TransactionLog getTransactions() {
		return transactions;
	}
	
	
}