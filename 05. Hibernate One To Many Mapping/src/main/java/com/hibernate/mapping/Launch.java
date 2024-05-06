package com.hibernate.mapping;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Launch {
	public static void main(String[] args) {

		Configuration cfg = new Configuration();
		cfg.configure();

		cfg.addAnnotatedClass(Customer.class);
		cfg.addAnnotatedClass(CustomerDetails.class);
		cfg.addAnnotatedClass(Orders.class);

		Session session = cfg.buildSessionFactory().openSession();
		Transaction t = session.beginTransaction();

		// Create
		Customer c = new Customer(4, "Mohan");
		CustomerDetails cd = new CustomerDetails(104, "Mohan@gmail.com", 8888000000L, "Delhi");
		c.setCustomerDetails(cd);

		Orders o1 = new Orders(507, "Manchurian", 129.49);
		Orders o2 = new Orders(508, "Kheer", 249.99);

		o1.setCustomer(c);
		o2.setCustomer(c);
		
		// session.persist(o1);
		// session.persist(o2);
		
		
		// Read
		Customer customer = session.get(Customer.class, 1);
		CustomerDetails customerDetails = customer.getCustomerDetails();
		
		List<Orders> orders = customer.getOrders();
		
		System.out.println(customer);
		System.out.println(customerDetails);
		
		for (Orders order : orders) {
			System.out.println(order.toString());
		}
		
		
		// Delete
		Customer cust = session.get(Customer.class, 3);
		
		if(cust != null) {
			// session.remove(cust);
			// System.out.println("Customer Deleted!!");
		} else {
			System.out.println("Customer not present with this Id!!");
		}
		
		Orders ord = session.get(Orders.class, 506);
		// session.remove(ord);
		
		CustomerDetails custDetails = session.get(CustomerDetails.class, 103);
		// session.remove(custDetails);

		t.commit();
	}
}
