package com.codeartcommercials.core;

public class Customer {
	private String ID;
	private String name;
	private String address;
	private String NIC;
	private String tpNo;
	private int age;
	private String emailAddress;
	
	public Customer(String ID, String name, String address, String NIC, String tpNo, int age, String emailAddress) {
		this.ID = ID;
		this.name = name;
		this.address = address;
		this.NIC = NIC;
		this.tpNo = tpNo;
		this.age = age;
		this.emailAddress = emailAddress;
	}
	
	public void displayCustomerDetails() {
		System.out.println("Customer ID      : " + getID());
		System.out.println("Name             : " + getID());
		System.out.println("NIC              : " + getID());
		System.out.println("Telephone number : " + getTpNo());
		System.out.println("E-mail           : " + getEmailAddress());
		System.out.println("Age              : " + getAge());
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getNIC() {
		return NIC;
	}
	
	public void setNIC(String nIC) {
		NIC = nIC;
	}
	
	public String getTpNo() {
		return tpNo;
	}
	
	public void setTpNo(String tpNo) {
		this.tpNo = tpNo;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
