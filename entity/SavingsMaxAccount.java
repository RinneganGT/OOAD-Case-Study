package com.ilp.entity;

import java.util.ArrayList;

public class SavingsMaxAccount extends Product{

	public SavingsMaxAccount(String productCode, String productName, ArrayList<Service> services) {
		super(productCode, productName, services);
		// TODO Auto-generated constructor stub
	}

	private double minimumBalance;

	public double getMinimumBalance() {
		return minimumBalance;
	}

	public void setMinimumBalance(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}
	
}
