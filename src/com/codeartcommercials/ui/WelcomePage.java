package com.codeartcommercials.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.codeartcommercials.exceptions.ImpossibleWithdrawAmountException;
import com.codeartcommercials.exceptions.InvalidBalanceException;
import com.codeartcommercials.exceptions.InvalidCurrencyException;
import com.codeartcommercials.exceptions.InvalidPasswordException;
import com.codeartcommercials.service.AccountManager;
import com.codeartcommercials.service.Customer;

class WelcomePage {
	
	private static AccountManager mgr = AccountManager.getAccMgr();
	
	public static void main(String[] args) {
		while(true) {
			switch(mainMenu()) {
			case 1:
				withdrawMoney();
				break;
				
			case 2:
				depositMoney();
				break;
				
			case 3:
				transferMoney();
				break;
				
			case 4:
				//createAccount() function called
				break;
				
			case 5:
				//closeAccount() function called
				break;
				
			default:
				
			}
		}
	}
	
	@SuppressWarnings("resource")
	public static int mainMenu() {
		Scanner obj = new Scanner(System.in);
		int option = 0;
		System.out.println("~~~~~~~~ WELCOME TO CODEART_COMMERCIALS ~~~~~~~~\n\n");
		System.out.println("Select a service from the menu given below.");
		System.out.println("1 Withdraw money");
		System.out.println("2 Deposit money");
		System.out.println("3 Transfer money");
		System.out.println("4 Create a new bank account");
		System.out.println("5 Close an existing account");
		System.out.println("-----------------------------");
		System.out.println("Enter the option number:");
		
		try {
			option = obj.nextInt();
			
			if(option != 1 && option != 2 && option != 3 && option != 4 && option != 5) {
				throw new InputMismatchException("Invalid input!!! Input should be an integer from 1 to 5.");
			}
		}catch(InputMismatchException e) {
			System.out.println(e.getMessage());
			obj.close();
			return 0;
		}
		obj.close();
		return option;
	}
	
	public static void withdrawMoney() {
		Scanner obj = new Scanner(System.in);
		String accNo , pin;
		float amount;
		int internalAmount;//amount in cents as an integer
		System.out.print("Enter account number                  : ");
		accNo = obj.next();
		System.out.print("Enter pin number                      : ");
		pin = obj.next();
		System.out.print("Enter the amount you want to withdraw : ");
		amount = obj.nextFloat();
		internalAmount = (int)amount*100;
		try {
			mgr.getAccountByOwner(accNo, pin).withdraw(internalAmount);
			System.out.println("Withdrawal succesful!");
			mgr.getAccountByOwner(accNo, pin).displayBalance();
		} catch (InvalidCurrencyException | InvalidBalanceException | ImpossibleWithdrawAmountException
				| InvalidPasswordException e) {
			e.printStackTrace();
		}
		obj.close();
	}
	
	public static void depositMoney() {
		Scanner obj = new Scanner(System.in);
		String accNo;
		float amount;
		int internalAmount;//amount in cents as an integer
		System.out.print("Enter account number                 : ");
		accNo = obj.next();
		System.out.print("Enter the amount you want to deposit : ");
		amount = obj.nextFloat();
		internalAmount = (int)amount*100;
		try {
			mgr.getAccount(accNo).deposit(internalAmount);
			System.out.println("Deposit succesfull!");
		} catch (InvalidCurrencyException | InvalidBalanceException e) {
			e.printStackTrace();
		}
		obj.close();
	}
	
	public static void transferMoney() {
		Scanner obj = new Scanner(System.in);
		String senderAccNo , pin , receiverAccNo;
		float amount;
		int internalAmount;//amount in cents as an integer
		System.out.print("Enter your account number             : ");
		senderAccNo = obj.next();
		System.out.print("Enter pin number                      : ");
		pin = obj.next();
		System.out.print("Enter the receiver's account number   : ");
		receiverAccNo = obj.next();
		System.out.print("Enter the amount you want to transfer : ");
		amount = obj.nextFloat();
		internalAmount = (int)amount*100;
		try {
			mgr.getAccountByOwner(senderAccNo, pin).transferMoney(receiverAccNo, internalAmount);
			System.out.println("Money transfer succesfull!");
			mgr.getAccountByOwner(receiverAccNo,pin).displayBalance();
		} catch (InvalidCurrencyException | InvalidBalanceException | ImpossibleWithdrawAmountException
				| InvalidPasswordException e) {
			e.printStackTrace();
		}
		obj.close();
	}
	
	public static void createAccount() {
		//create customer
		Customer owner = createCustomer();
		//account
		String ID,type,branch,pin;
		mgr.createNewAccount(null, null, null, 0, null, null);
	}
	
	public static void closeAccount() {
		
	}
	
	public static Customer createCustomer() {
		String ID , name , address , NIC , tpNo ,age , emailAddress;
		
	}

}
