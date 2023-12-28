package com.ilp.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.Customer;
import com.ilp.entity.Product;
import com.ilp.entity.Service;
import com.ilp.service.BankService;
//import com.ilp.service.CustomerAccountService;

public class BankUtility {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		mainMenu();
		
		
	}
	
		
		private static void mainMenu() {
		// TODO Auto-generated method stub
			
			
			Scanner scanner = new Scanner(System.in);
			ArrayList<Service> serviceList = new ArrayList<Service>();
			ArrayList<Product> productList = new ArrayList<Product>();
			ArrayList<Account> accountList = new ArrayList<Account>();
//			Customer customer = BankService.createCustomer(productList);
			Customer customer = null;



			char mainMenuChoice = 'y';
			
			do {
				System.out.println("1. Create Service, 2. Create Product, 3. Create Customer, 4. Manage Accounts , 5.Display Customer, 6.Exit .");
				int subMenuChoice = scanner.nextInt();
		 
				
				switch(subMenuChoice)
				{
				
				case 1:{
					 BankService.createService(serviceList);
					 System.out.println(serviceList);
					 break;
				           
				}
				
				case 2:{
					BankService.createProduct(productList,serviceList);
					for(Product service:productList) {
						System.out.println("Service List"+ service.toString());
					}
					break;
					
				}
				
				case 3:{
					customer = BankService.createCustomer(productList);
					break;
				}
				
				case 4:{
					BankService.manageAccount(customer);
					break;
				}
				
				case 5:{
					BankService.displayCustomer(customer);
					break;
				}
				}
				
				System.out.println("Do you want to go back to main menu ? (y/n)");
				mainMenuChoice = scanner.next().charAt(0);


		}
			while(mainMenuChoice == 'y');
		
	}


		

}

