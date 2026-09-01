package com.studentManagement;

abstract class Person {
	//attributes
	private Integer id;
	private String name;
	
	Person(Integer id, String name){
		this.id = id;
		this.name = name;
	}
	
	//getters
	public abstract String getRole();
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	//setters
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
