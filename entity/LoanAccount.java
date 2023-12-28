package com.ilp.entity;

import java.util.ArrayList;

public class LoanAccount extends Product{
	
	public LoanAccount(String productCode, String productName, ArrayList<Service> services) {
		super(productCode, productName, services);
		// TODO Auto-generated constructor stub
	}

	
	private double chequeDeposit;

	public double getChequeDeposit() {
		return chequeDeposit;
	}

	public void setChequeDeposit(double chequeDeposit) {
		this.chequeDeposit = chequeDeposit;
	}
	
	
}
