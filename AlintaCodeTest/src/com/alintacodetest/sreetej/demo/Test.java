package com.alintacodetest.sreetej.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.alintacodetest.sreetej.entity.Customer;

public class Test {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
		
		 
		  if(args[0].equalsIgnoreCase("Create")) {
			  String firstName=args[1];
			  String lastName=args[2];
			  String sDate1=args[3]; 
		  Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
		    System.out.println(sDate1+"\t"+date1);
		// create session factory
				SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Customer.class)
										.buildSessionFactory();
				
				// create session
				Session session = factory.getCurrentSession();
				
				try {			
					// create a student object
					System.out.println("Creating new student object...");
					Customer c1 = new Customer(firstName,lastName,date1);
					
					// start a transaction
					session.beginTransaction();
					
					// save the student object
					System.out.println("Saving the student...");
					session.save(c1 );
					
					// commit transaction
					session.getTransaction().commit();
					
					System.out.println("Done!");
				}
				finally {
					factory.close();
				}
		  }
		  
		  if(args[0].equalsIgnoreCase("Search")) {
			  String name=args[1];
			  SessionFactory factory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Customer.class)
						.buildSessionFactory();

			  // create session
			  Session session = factory.getCurrentSession();
			  try {			
 					// start a transaction
					session.beginTransaction();
 					// query customers
					List<Customer> theCustomers = session.createQuery("from Customer").getResultList();
 					theCustomers = session.createQuery("from Customer s where s.lastName="+"'"+name+"'").getResultList();
 					// display the customers
 					displayCustomers(theCustomers);
 					// query customers
					theCustomers =session.createQuery("from Customer s where" + " s.lastName="+"'"+name+"'" +"OR s.firstName="+"'"+name+"'").getResultList();
  					displayCustomers(theCustomers);
 					// commit transaction
					session.getTransaction().commit();
 					System.out.println("Done!");
				}
				finally {
					factory.close();
				}
		  }
		  
		  if(args[0].equalsIgnoreCase("Update")) {
			  String name=args[1];
			  String updatedFirstName=args[2];
			  String updatedLastName=args[3];
			  SessionFactory factory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Customer.class)
						.buildSessionFactory();

			  // create session
			  Session session = factory.getCurrentSession();
			  try {			
 					// start a transaction
					session.beginTransaction();
 					// query customers
					List<Customer> theCustomers = session.createQuery("from Customer").getResultList();
					
					for(int i=0;i<theCustomers.size();i++) {
						
						if(theCustomers.get(i).getFirstName().equalsIgnoreCase(name)||theCustomers.get(i).getLastName().equalsIgnoreCase(name) ) {
							
							int custId=theCustomers.get(i).getId();
							Customer myCustomer = session.get(Customer.class, custId);
							myCustomer.setFirstName(updatedFirstName);
							//session.getTransaction().commit();
							myCustomer.setLastName(updatedLastName);
 							// commit the transaction
							session.getTransaction().commit();
							
						}
					}
					
 				}
				finally {
					factory.close();
				}
		  }
		  
		  
		  if(args[0].equalsIgnoreCase("Delete")) {
			  String name=args[1];
			  //String updatedFirstName=args[2];
			  //String updatedLastName=args[3];
			  SessionFactory factory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Customer.class)
						.buildSessionFactory();

			  // create session
			  Session session = factory.getCurrentSession();
			  try {			
 					// start a transaction
					session.beginTransaction();
 					// query customers
					List<Customer> theCustomers = session.createQuery("from Customer").getResultList();
					
					for(int i=0;i<theCustomers.size();i++) {
						
						if(theCustomers.get(i).getFirstName().equalsIgnoreCase(name)||theCustomers.get(i).getLastName().equalsIgnoreCase(name) ) {
							
							int custId=theCustomers.get(i).getId();
							//Customer myCustomer = session.get(Customer.class, custId);
							//myCustomer.setFirstName(updatedFirstName);
							//session.getTransaction().commit();
							//myCustomer.setLastName(updatedLastName);
							
							session.createQuery("delete from Customer where id="+"'"+custId+"'").executeUpdate();
 							// commit the transaction
							session.getTransaction().commit();
							
						}
					}
					
 				}
				finally {
					factory.close();
				}
		  }
	 
	}
	
	private static void displayCustomers(List<Customer> theCustomers) {
		for (Customer tempStudent : theCustomers) {
			System.out.println(tempStudent);
		}

}
}
