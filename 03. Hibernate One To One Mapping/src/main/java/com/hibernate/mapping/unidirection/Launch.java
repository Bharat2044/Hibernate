package com.hibernate.mapping.unidirection;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class Launch {
	public static void main(String[] args) {
		
		Configuration cfg = new Configuration();
		cfg.configure();
		
		cfg.addAnnotatedClass(Customer.class);
		cfg.addAnnotatedClass(CustomerDetails.class);
		
		Session session = cfg.buildSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		
		// Create
		Customer c1 = new Customer(1, "Tim");
		CustomerDetails cd1 = new CustomerDetails(101, "tim@gmail.com", 9900880099L, "Banglore");
		c1.setCustomerDetails(cd1);
		
		// session.save(c1);
		//session.persist(c1);
		
		Customer c2 = new Customer(2, "Mike");
		CustomerDetails cd2 = new CustomerDetails(102, "mike@gmail.com", 7777880099L, "New York");
		c2.setCustomerDetails(cd2);
		// session.persist(c2);
		
		// Read
		int id = 2;
		Customer c = session.get(Customer.class, id);
		CustomerDetails cd = c.getCustomerDetails();
		
		if(c != null) {
			/*
			System.out.print(c.getId() + "  ");
			System.out.print(c.getName() + "  ");
			System.out.print(cd.getId() + "  ");
			System.out.print(cd.getEmail() + "  ");
			System.out.print(cd.getPhone() + "  ");
			System.out.print(cd.getAddress() + "  ");
			*/
			System.out.println(c.toString());
		} else {
			System.out.println("Custome is not present with Id: " + id);
		}
		
		Query<Customer> query = session.createQuery("FROM Customer c", Customer.class);
		List<Customer> customers = query.getResultList();
		
		if(!customers.isEmpty()) {
			for (Customer customer : customers) {
				System.out.println(customer.toString());
			}
		} else {
			System.out.println("No Customers Found!!");
		}
		
		
		// Delete
		if(c != null) {
			// session.remove(c);
		} else {
			System.out.println("Custome is not present with Id: " + id);
		}

		t.commit();
	}
}
