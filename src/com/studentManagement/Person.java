package com.studentManagement;

abstract class Person {
	//attributes
	protected int id;
	protected String name;
	
	Person(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	//getters
	public abstract String getRole();
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	//setters
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
