package com.ticket.app.payment.model;

public class User {
	
	private String name;
	private String age;
	private String adress;
	
	public User() {
	}
	
	public User(String name, String age, String adress) {
		this.name = name;
		this.age = age;
		this.adress = adress;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}


	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", adress=" + adress + "]";
	}
	
	

}
