package com.codeartcommercials.service;

class Customer {
	private String ID;
	private String name;
	private String address;
	private String NIC;
	private String tpNo;
	private String age;
	private String emailAddress;
	
	public Customer(String ID, String name, String address, String NIC, String tpNo, String age, String emailAddress) {
		this.ID = ID;
		this.name = name;
		this.address = address;
		this.NIC = NIC;
		this.tpNo = tpNo;
		this.age = age;
		this.emailAddress = emailAddress;
	}

	public String getID() {
		return ID;
	}
	
	public void setID(String iD) {
		ID = iD;
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
	
	public String getAge() {
		return age;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
