package com.project.jdbc.crud.domain;

public class Type {
	private long id;
	private String name;
	private String purpose;
	
	public Type(){	}
	
	public Type(String name, String purpose){
		this.name = name;
		this.purpose = purpose;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
}
