package com.codeartcommercials.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TransactionLog {
	private File transactionLog;
	
	public TransactionLog(String ID) {
		this.transactionLog = new File("TL" + ID+".txt");
		try {
			if(transactionLog.createNewFile()) {
				System.out.println("New transaction log created succesfully");
			}else {
				System.out.println("transaction log already exists");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void write(int amount , int balance) {
		try {
			FileWriter fw = new FileWriter(transactionLog,true);
			fw.write(java.time.LocalDateTime.now()+ "\t" + amount + "\t" + balance + "\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void displayTransactionLog() {
		try {
			Scanner obj = new Scanner(this.transactionLog);
			while(obj.hasNextLine()) {
				String data = obj.nextLine();
				System.out.println(data);
			}
			obj.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}	
	}
}
