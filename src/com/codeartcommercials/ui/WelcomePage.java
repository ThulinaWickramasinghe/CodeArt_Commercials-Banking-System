package com.codeartcommercials.ui;

import com.codeartcommercials.core.*;
import com.codeartcommercials.exceptions.*;
import java.util.InputMismatchException;
import java.util.Scanner;



class WelcomePage {
	
	private static AccountManager mgr = AccountManager.getAccMgr();
	private static Scanner sc = new Scanner(System.in);
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
		String accNo , pin;
		System.out.print("Enter account number                  : ");
		accNo = sc.next();
		System.out.print("Enter pin number                      : ");
		pin = sc.next();
	
		try {
			mgr.getAccountByOwner(accNo, pin).displayTransactionHistory();
		} catch (InvalidPasswordException e) {
			e.printStackTrace();
		}
		
	}

	private static void viewAccountDetails() {
		String accNo , pin;
		System.out.print("Enter account number                  : ");
		accNo = sc.next();
		System.out.print("Enter pin number                      : ");
		pin = sc.next();
	
		try {
			mgr.getAccountByOwner(accNo, pin).displayAccountDetails();
		} catch (InvalidPasswordException e) {
			e.printStackTrace();
		}
		
	}

//	@SuppressWarnings("resource")
	public static int mainMenu() {
		int option = 0;
		System.out.println("~~~~~~~~ WELCOME TO CODEART_COMMERCIALS ~~~~~~~~\n\n");
		System.out.println("Select a service from the menu given below.");
		System.out.println("1 Withdraw money");
		System.out.println("2 Deposit money");
		System.out.println("3 Transfer money");
		System.out.println("4 Create a new bank account");
		System.out.println("5 Close an existing account");
		System.out.println("6 View account details");
		System.out.println("7 View transaction history");
		System.out.println("-----------------------------");
		System.out.print("Enter the option number:");
		
		try {
			option = sc.nextInt();
 			sc.nextLine();
			if(option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option!= 6 && option != 7) {
				throw new InputMismatchException("Invalid input!!! Input should be an integer from 1 to 5.");
			}
		}catch(InputMismatchException e) {
			System.out.println(e.getMessage());
			option = 0;
		}

		return option;
	}
	
	public static void withdrawMoney() {

		String accNo , pin;
		float amount;
		int internalAmount;//amount in cents as an integer
		System.out.print("Enter account number                  : ");
		accNo = sc.next();
		System.out.print("Enter pin number                      : ");
		pin = sc.next();
		System.out.print("Enter the amount you want to withdraw : ");
		amount = sc.nextFloat();
		internalAmount = (int)amount*100;
		try {
			mgr.getAccountByOwner(accNo, pin).withdraw(internalAmount);
			System.out.println("Withdrawal succesful!");
			mgr.getAccountByOwner(accNo, pin).displayBalance();
		} catch (InvalidCurrencyException | InvalidBalanceException | ImpossibleWithdrawAmountException
				| InvalidPasswordException e) {
			e.printStackTrace();
		}

	}
	
	public static void depositMoney() {
		String accNo;
		float amount;
		int internalAmount;//amount in cents as an integer
		System.out.print("Enter account number                 : ");
		accNo = sc.next();
		System.out.print("Enter the amount you want to deposit : ");
		amount = sc.nextFloat();
		internalAmount = (int)amount*100;
		try {
			mgr.getAccount(accNo).deposit(internalAmount);
			System.out.println("Deposit succesfull!");
		} catch (InvalidCurrencyException | InvalidBalanceException e) {
			e.printStackTrace();
		}
	}
	
	public static void transferMoney() {
		String senderAccNo , pin , receiverAccNo;
		float amount;
		int internalAmount;//amount in cents as an integer
		System.out.print("Enter your account number             : ");
		senderAccNo = sc.next();
		System.out.print("Enter pin number                      : ");
		pin = sc.next();
		System.out.print("Enter the receiver's account number   : ");
		receiverAccNo = sc.next();
		System.out.print("Enter the amount you want to transfer : ");
		amount = sc.nextFloat();
		internalAmount = (int)amount*100;
		try {
			mgr.getAccountByOwner(senderAccNo, pin).transferMoney(receiverAccNo, internalAmount);
			System.out.println("Money transfer succesfull!");
			mgr.getAccountByOwner(receiverAccNo,pin).displayBalance();
		} catch (InvalidCurrencyException | InvalidBalanceException | ImpossibleWithdrawAmountException
				| InvalidPasswordException e) {
			e.printStackTrace();
		}
	}
	
	public static void createAccount() {
		//create customer
		Customer owner = createCustomer();
		
		//create account

		String ID,type,branch,pin;
		float balance;
		int innerBalance;
		
		System.out.println("Enter the account number : ");
		ID = sc.nextLine();
		
		System.out.println("Enter the account type : ");
		type = sc.nextLine();
		
		System.out.println("Enter the initial deposit amount(Should be greater than or equal to Rs.1500.00): ");
		balance = sc.nextFloat();
		sc.nextLine();
		
		innerBalance = (int)(balance*100);
		
		System.out.println("Enter the branch : ");
		branch = sc.nextLine();
		
		System.out.println("Enter the pin : ");
		pin = sc.nextLine();
		
		try {
			mgr.createNewAccount(ID, type, owner , innerBalance, branch, pin);
			System.out.println("New account successfully created!");
		} catch (InvalidCurrencyException | InvalidBalanceException | InvalidPasswordFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public static void closeAccount() {
		
		String accNo,pin;
		
		System.out.println("Enter the account number:");
		accNo = sc.nextLine();
		
		System.out.println("Enter the pin           :");
		pin = sc.nextLine();
		
		try {
			mgr.closeAccount(accNo, pin);
		} catch (InvalidPasswordException e) {
			e.printStackTrace();
		}

	}
	
	public static Customer createCustomer() {
		String name , address , NIC , tpNo , emailAddress;
		int age;

		System.out.print("Enter your customer ID:");
		String ID = sc.nextLine();
		
		System.out.print("Enter your name:");
	    name = sc.nextLine();
		
		System.out.println("Enter your address:");
		address = sc.nextLine();
		
		System.out.println("Enter your NIC:");
		NIC = sc.nextLine();
		
		System.out.println("Enter your telephone number:");
		tpNo = sc.nextLine();
		
		System.out.println("Enter your age:");
		age = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter your email address:");
		emailAddress = sc.nextLine();
		
		Customer owner = new  Customer(ID, name, address, NIC,tpNo,age,emailAddress);
		return owner;
	}

}
