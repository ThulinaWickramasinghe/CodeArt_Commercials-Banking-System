package com.codeartcommercials.core;

import java.time.LocalDate;
import com.codeartcommercials.exceptions.ImpossibleWithdrawAmountException;
import com.codeartcommercials.exceptions.InvalidBalanceException;
import com.codeartcommercials.exceptions.InvalidCurrencyException;
import com.codeartcommercials.exceptions.InvalidPasswordFormatException;

public class Account {
	
	private String ID;
	private String type;
	private Customer owner;
	private String branch;
	private int balance; //balance will be stored using integer data type and measured using cents(Sri Lankan) because of issues of floating point data type with currency
	private TransactionLog transactions;
	private LocalDate startDate;
	private String password; 


	public Account(String ID, String type, Customer owner, int amount, String branch , String pin) throws InvalidCurrencyException, InvalidBalanceException, InvalidPasswordFormatException {
		super();
		transactions = new TransactionLog(this.ID);
		setID(ID);
		setType(type);
		setOwner(owner);
		deposit(amount);
		setBranch(branch);
		setStartDate();
		setPassword(pin);
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
	
	public void transferMoney(String receiverAccID , int amount) throws InvalidCurrencyException, InvalidBalanceException, ImpossibleWithdrawAmountException {
		withdraw(amount);
		AccountManager.getAccMgr().getAccount(receiverAccID).deposit(amount);
	}
	
	public void displayAccountDetails() {
		System.out.println("Account ID    : " + getID());
		System.out.println("Account type  : " + getType());
		System.out.println("Account owner : " + getOwner().getName());
		System.out.println("Branch        : " + getBranch());
		System.out.println("Start date    : " + getStartDate());
		displayBalance();
	}
	
	public void displayTransactionHistory() {
		this.getTransactions().displayTransactionLog();
	}
	
	public void displayBalance() {
		System.out.println("Your account balance is Rs." + getBalance()/100 + "." + getBalance()%100);
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) throws InvalidPasswordFormatException {
		if(password.length() < 8) {
			throw new InvalidPasswordFormatException();
		}else {
			this.password = password;
		}
	}
	
	public void setStartDate() {
		this.startDate = java.time.LocalDate.now();
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
	
	private TransactionLog getTransactions() {
		return transactions;
	}
	
	
}
