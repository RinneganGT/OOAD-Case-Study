package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.CurrentAccount;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;

public class BankService {

	public static ArrayList<Service> createService(ArrayList<Service> serviceList) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter Service Code :");
		String serviceCode = scanner.nextLine();

		System.out.println("Enter Service Name :");
		String serviceName = scanner.nextLine();

		System.out.println("Enter Service Rate :");
		double serviceRate = scanner.nextDouble();

		Service service = new Service(serviceCode, serviceName, serviceRate);
		serviceList.add(service);

		return serviceList;

	}

	public static void createProduct(ArrayList<Product> productList, ArrayList<Service> serviceList) {

		Product product = null;
		String serviceCode;
		ArrayList<Service> productServiceList = new ArrayList<Service>();
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter Product Code :");
		String productCode = scanner.nextLine();

		System.out.println("Enter Product Name :");
		String productName = scanner.nextLine();
		char serviceChoice;

		do {

			System.out.println("Enter the Product you want to attach the service to : ");
			System.out.println("Products Available : ");
			System.out.println("1.Savings Max Account , 2.Current Account , 3.Loan Account");

			int productChoice = scanner.nextInt();
			scanner.nextLine();

			System.out.println("Enter Service you want to attach? :");

			for (Service service : serviceList) {
				System.out.println(
						"Service Code: " + service.getServiceCode() + ", Service Name: " + service.getServiceName());
			}

			System.out.println("Enter the Service code needed for product from service list above");
			serviceCode = scanner.nextLine();

			Service selectedService = null;

			for (Service service : serviceList) {

				if (service.getServiceCode().equals(serviceCode)) {
					selectedService = service;
					break;
				}
			}

			productServiceList.add(selectedService);


			switch (productChoice) {

			case 1:

				product = new SavingsMaxAccount(productCode, productName, productServiceList);
				productList.add(product);
				break;

			case 2:

				product = new CurrentAccount(productCode, productName, productServiceList);
				productList.add(product);
				break;

			case 3:

				product = new LoanAccount(productCode, productName, productServiceList);
				productList.add(product);
				break;

			default:

				System.out.println("Product not identified");
				break;

			}

			System.out.println("Do you want to attach another service? ('y/n)");
			serviceChoice = scanner.next().charAt(0);

		} while (serviceChoice == 'y');

	}

	public static Customer createCustomer(ArrayList<Product> productList) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter Customer Code :");
		String customerCode = scanner.nextLine();

		System.out.println("Enter Customer Name :");
		String customerName = scanner.nextLine();

		ArrayList<Account> accountList = new ArrayList<Account>();
//		ArrayList<Customer> customerList = new ArrayList<Customer>();

		accountList = createAccounts(productList);

		Customer customer = new Customer(customerCode, customerName, accountList);

//	    // Print Customer details directly
//	    System.out.println("Customer Code: " + customer.getCustomerCode());
//	    System.out.println("Customer Name: " + customer.getCustomerName());
//
//	    // Print Account details directly
//	    for (Account account : accountList) {
//	        System.out.println("Account Number: " + account.getAccountNo());
//	        System.out.println("Account Type: " + account.getAccountType());
//	        System.out.println("Balance: " + account.getBalance());
//	        System.out.println("Product Type: " + account.getProduct().getProductName());
//	        System.out.println("------------");
//	    }

		return customer;

	}

	private static ArrayList<Account> createAccounts(ArrayList<Product> productList) {

		Scanner scanner = new Scanner(System.in);
		ArrayList<Account> accountList = new ArrayList<Account>();
		char multipleAccountChoice = 'y';

		do {

			System.out.println("Enter Account Number :");
			String accountNo = scanner.nextLine();

			System.out.println("Enter Account Type :");
			String accountType = scanner.nextLine();

			System.out.println("Enter Balance:");
			double balance = scanner.nextDouble();
			
			System.out.println("Product Choice :");
			System.out.println("1.Savings Max Account , 2.Current Account , 3.Loan Account");
			int productChoice = scanner.nextInt();

			Product selectedProduct = productList.get(productChoice - 1); // Index and Option differ
			Account account = new Account(accountNo, accountType, balance, selectedProduct);
			accountList.add(account);
//		    customerList.add(customer);

			System.out.println("Do you want to create another account? (y/n)");
			multipleAccountChoice = scanner.next().charAt(0);
			scanner.nextLine();
			
		} while (multipleAccountChoice == 'y');

		return accountList; // Modify this line as needed
	}

//	public static Customer displayCustomer(ArrayList<Product> productList) {
//		
//	
//		
//	    Customer customer = new Customer(customerCode, customerName, accountList);
//	    
//	    
//	    // Print Customer details directly
//	    System.out.println("Customer Code: " + customer.getCustomerCode());
//	    System.out.println("Customer Name: " + customer.getCustomerName());
//
//	    // Print Account details directly
//	    for (Account account : accountList) {
//	        System.out.println("Account Number: " + account.getAccountNo());
//	        System.out.println("Account Type: " + account.getAccountType());
//	        System.out.println("Balance: " + account.getBalance());
//	        System.out.println("Product Type: " + account.getProduct().getProductName());
//	        System.out.println("------------");
//	    }
//
//		return customer;
//		
//	}

	public static void manageAccount(Customer customer) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter Customer ID :");
		String customerCode = scanner.nextLine();
		String accountChoice;

		if (customer.getCustomerCode().equals(customerCode)) {
			System.out.println(customer.getCustomerName() + " has following accounts :");

			for (Account account : customer.getAccounts()) {
				if (customer.getCustomerCode().equals(customerCode)) {
					System.out.println("Account Number: " + account.getAccountNo());
					System.out.println("Account Type: " + account.getAccountType());
//				                    System.out.println("Balance: " + account.getBalance());
					System.out.println("----------------------------");
				}
			}
			System.out.println("Enter your choice : ");
			accountChoice = scanner.nextLine();

			int serviceChoice;
			double deposit;
			char anotherService;
			int depositChoice;

			for (Account account : customer.getAccounts()) {
				double balance = account.getBalance();

				if (accountChoice.equals(account.getAccountNo())) {

					System.out.println("Enter your choice:");
					System.out.println("1.Deposit  2.Withdraw  3.Display Balance");
					serviceChoice = scanner.nextInt();

					switch (serviceChoice) {

					case 1:
						depositMoney(account);
						break;

					case 2:
						withdrawMoney(account);
						break;

					case 3:
						displayBalance(account);
						break;
					}

				}
//				                	
//				                	System.out.println("1.Deposit , 2.Withdraw , 3.Display Balance");
//				                	System.out.println();
//				                	System.out.println("Enter your choice :");
//				                	serviceChoice=scanner.nextInt();
//				                	
//				                	switch(serviceChoice) {
//				                	
//				                	case 1:
//				                		System.out.println("Enter amount to be deposited : ");
//				                		deposit = scanner.nextDouble();
//				                		double newBalance=account.getBalance()+deposit;
//				                		account.setBalance(newBalance);
//				                		System.out.println("Your current balance  : " + newBalance);
//				                		break;
//				                		
//				                	case 2:
//					                		System.out.println("Enter amount to be withdrawn : ");
//					                		deposit = scanner.nextDouble();
//					                		if((account.getBalance()-deposit)<1000)
//					                			System.out.println("Minimum balance of 1000 should be manintained");
//					                		
//					                		else {
//					                			double newBalanceWithdraw=account.getBalance()-deposit;
//					                		account.setBalance(newBalanceWithdraw);
//					                		System.out.println("Your current balance  : " + newBalanceWithdraw);
//					                		}
//					                		break;
//				                	
//				                	case 3:
//				                		System.out.println("Current Balance: " + account.getBalance());	
//				                		break;
//				                	}
//				                	System.out.println("Do you want to continue? (y/n");
//				                	anotherService = scanner.next().charAt(0);
//				                	}while(anotherService == 'y');
//				                }
//				                	
//				               else if(accountChoice=="Current Account") {
//				            	   
//				            	   do {
//				                		
//					                	
//					                	System.out.println("1.Deposit , 2.Withdraw , 3.Display Balance");
//					                	System.out.println();
//					                	System.out.println("Enter your choice :");
//					                	serviceChoice=scanner.nextInt();
//					                	
//					                	switch(serviceChoice) {
//					                	
//					                	case 1:
//					                		System.out.println("Enter amount to be deposited : ");
//					                		deposit = scanner.nextDouble();
//					                		System.out.println("Deposit method ");
//					                		System.out.println("Cash / Cheque");
//					                		depositChoice= scanner.nextInt();
//					                		
//					                		switch(depositChoice){
//					                		case 1:
//					                				balance=balance+deposit;
//							                		System.out.println("Your current balance  : " + balance);		
//							                		
//					                		}
//					                		
//					                		System.out.println("Your current balance  : " + (account.getBalance()+deposit ));
//					                		break;
//					                		
//					                	case 2:
//						                		System.out.println("Enter amount to be withdrawn : ");
//						                		deposit = scanner.nextDouble();
//						                		System.out.println("Your current balance  : " + (account.getBalance()-deposit) );
//						                		break;
//					                	
//					                	case 3:
//					                		System.out.println("Current Balance: " + account.getBalance());	
//					                		break;
//					                	}
//					                	System.out.println("Do you want to continue? (y/n");
//					                	anotherService = scanner.next().charAt(0);
//					                	}while(anotherService == 'y');
//				               }
//				                	
//				                	
//				                	
//				                	
//				                	
//				                
//				                	
//				                }
//				                
//			                else 
4
			}
		}

	}

	private static void displayBalance(Account account) {

		System.out.println("Current Balance: " + account.getBalance());
	}

	private static void withdrawMoney(Account account) {

		Scanner scanner = new Scanner(System.in);

		if (account.getAccountType().equalsIgnoreCase("Savings Max Account")) {

			System.out.println("Enter amount to be withdrawn : ");
			double deposit = scanner.nextDouble();
			double newBalance = account.getBalance() - deposit;

			if ((newBalance) < 1000)
				System.out.println("Minimum balance of 1000 should be manintained");

			else {
				double newBalanceWithdraw = account.getBalance() - deposit;
				account.setBalance(newBalanceWithdraw);
				System.out.println("Your current balance  : " + newBalanceWithdraw);
			}

		} else if (account.getAccountType().equalsIgnoreCase("Current Account")) {

			System.out.println("Enter amount to be withdrawn : ");
			double deposit = scanner.nextDouble();
			double newBalance = account.getBalance() - deposit;

			if ((newBalance) < 0)
				System.out.println("Account cannot be empty");

			else {
				double newBalanceWithdraw = account.getBalance() - deposit;
				account.setBalance(newBalanceWithdraw);
				System.out.println("Your current balance  : " + newBalanceWithdraw);
			}

		} else if (account.getAccountType().equalsIgnoreCase("Loan Account")) {

			System.out.println("Cannot Withdraw from Loan Account ");

		}

	}

	private static void depositMoney(Account account) {

		Scanner scanner = new Scanner(System.in);

		if (account.getAccountType().equalsIgnoreCase("Savings Max Account")) {

			System.out.println("Enter amount to be deposited : ");
			double deposit = scanner.nextDouble();
			double newBalance = account.getBalance() + deposit;
			account.setBalance(newBalance);
			System.out.println("Your current balance  : " + newBalance);

		} else if (account.getAccountType().equalsIgnoreCase("Current Account")) {

			System.out.println("Enter amount to be deposited : ");
			double deposit = scanner.nextDouble();
			double newBalance = account.getBalance() + deposit;
			account.setBalance(newBalance);
			System.out.println("Your current balance  : " + newBalance);

		} else if (account.getAccountType().equalsIgnoreCase("Loan Account")) {

			System.out.println("Deposit method ");
			System.out.println("1.Cash / 2.Cheque");
			int depositChoice = scanner.nextInt();

			switch (depositChoice) {

			case 1:
				System.out.println("Enter amount to be deposited : ");
				double deposit = scanner.nextDouble();
				double newBalance = account.getBalance() + deposit;
				account.setBalance(newBalance);
				System.out.println("Your current balance  : " + newBalance);
				break;

			case 2:
				System.out.println("Enter amount to be deposited : ");
				double depositCheque = scanner.nextDouble();
				double chargePercentage = 0.003; // 0.3% expressed as a decimal

				double chargeAmount = depositCheque * chargePercentage;

				double actualDepositAmount = depositCheque - chargeAmount;

				double newBalanceCheque = account.getBalance() + actualDepositAmount;
				account.setBalance(newBalanceCheque);
				System.out.println("Your current balance  : " + newBalanceCheque);
				break;
			}
		}

	}

	public static void displayCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
		
//		 Print Customer details directly
	    System.out.println("Customer Code: " + customer.getCustomerCode());
	    System.out.println("Customer Name: " + customer.getCustomerName());

	    // Print Account details directly
	    for (Account account : customer.getAccounts()) {
	        System.out.println("Account Number: " + account.getAccountNo());
	        System.out.println("Account Type: " + account.getAccountType());
	        System.out.println("Balance: " + account.getBalance());
//	        System.out.println("Product Type: " + account.getProduct().getProductName());
	        System.out.println("------------");
	    }
	}
}
