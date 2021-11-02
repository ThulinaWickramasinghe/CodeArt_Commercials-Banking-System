package com.codeartcommercials.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.codeartcommercials.exceptions.ImpossibleWithdrawAmountException;
import com.codeartcommercials.exceptions.InvalidBalanceException;
import com.codeartcommercials.exceptions.InvalidCurrencyException;
import com.codeartcommercials.exceptions.InvalidPasswordException;
import com.codeartcommercials.exceptions.InvalidPasswordFormatException;
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
				createAccount();
				break;
				
			case 5:
				closeAccount();
				break;
			
			case 6:
				viewAccountDetails();
				break;
			
			case 7:
				viewTransactionHistory();
				break;
				
			default:
				
			}
		}
	}
	
	private static void viewTransactionHistory() {
		Scanner obj = new Scanner(System.in);
		String accNo , pin;
		System.out.print("Enter account number                  : ");
		accNo = obj.next();
		System.out.print("Enter pin number                      : ");
		pin = obj.next();
	
		try {
			mgr.getAccountByOwner(accNo, pin).displayTransactionHistory();
		} catch (InvalidPasswordException e) {
			e.printStackTrace();
		}
		obj.close();
		
	}

	private static void viewAccountDetails() {
		Scanner obj = new Scanner(System.in);
		String accNo , pin;
		System.out.print("Enter account number                  : ");
		accNo = obj.next();
		System.out.print("Enter pin number                      : ");
		pin = obj.next();
	
		try {
			mgr.getAccountByOwner(accNo, pin).displayAccountDetails();
		} catch (InvalidPasswordException e) {
			e.printStackTrace();
		}
		obj.close();
		
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
		System.out.println("4 View account details");
		System.out.println("5 View transaction history");
		System.out.println("-----------------------------");
		System.out.println("Enter the option number:");
		
		try {
			option = obj.nextInt();
			
			if(option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option!= 6 && option != 7) {
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
		
		//create account
		Scanner obj = new Scanner(System.in);
		String ID,type,branch,pin;
		float balance;
		int innerBalance;
		
		System.out.println("Enter the account number : ");
		ID = obj.nextLine();
		
		System.out.println("Enter the account type : ");
		type = obj.nextLine();
		
		System.out.println("Enter the initial deposit amount(Should be greater than or equal to Rs.1500.00): ");
		balance = obj.nextFloat();
		innerBalance = (int)(balance*100);
		
		System.out.println("Enter the branch : ");
		branch = obj.nextLine();
		
		System.out.println("Enter the pin : ");
		pin = obj.nextLine();
		
		try {
			mgr.createNewAccount(ID, type, owner , innerBalance, branch, pin);
			System.out.println("New account successfully created!");
		} catch (InvalidCurrencyException | InvalidBalanceException | InvalidPasswordFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		obj.close();
		
	}
	
	public static void closeAccount() {
		Scanner obj = new Scanner(System.in);
		String accNo,pin;
		
		System.out.println("Enter the account number:");
		accNo = obj.nextLine();
		
		System.out.println("Enter the pin           :");
		pin = obj.nextLine();
		
		try {
			mgr.closeAccount(accNo, pin);
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		obj.close();
	}
	
	public static Customer createCustomer() {
		String ID , name , address , NIC , tpNo ,age , emailAddress;
		Scanner obj = new Scanner(System.in);
		System.out.println("Enter your customer ID:");
		ID = obj.nextLine();
		System.out.println("");
		name = obj.nextLine();
		System.out.println("");
		address = obj.nextLine();
		System.out.println("");
		NIC = obj.nextLine();
		System.out.println("");
		tpNo = obj.nextLine();
		System.out.println("");
		age = obj.nextLine();
		System.out.println("");
		emailAddress = obj.nextLine();
		obj.close();
		Customer owner = new  Customer(ID, name, address, NIC,tpNo,age,emailAddress);
		return owner;
	}

}
