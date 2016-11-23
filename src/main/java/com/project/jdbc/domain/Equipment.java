package com.project.jdbc.domain;

public class Equipment {
	private long id;
	private long typeId;
	private String model;
	private double price;

	public Equipment() {
	}

	public Equipment(int typeId, String model, double price) {
		this.typeId = typeId;
		this.model = model;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
