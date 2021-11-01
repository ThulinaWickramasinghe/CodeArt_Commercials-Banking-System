package com.codeartcommercials.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class TransactionLog {
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
}
