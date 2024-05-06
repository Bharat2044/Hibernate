package com.hibernate.mapping;

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
		cfg.addAnnotatedClass(Orders.class);

		Session session = cfg.buildSessionFactory().openSession();
		Transaction t = session.beginTransaction();

		// CRUD Operations
		// Create
		Customer c1 = session.get(Customer.class, 1);
		Customer c2 = session.get(Customer.class, 2);

		Orders o1 = new Orders(501, "Noodles", 99.99);
		o1.setCustomer(c1);

		Orders o2 = new Orders(502, "Burger", 149.49);
		o2.setCustomer(c1);

		Orders o3 = new Orders(503, "Momos", 79.99);
		o3.setCustomer(c1);

		Orders o4 = new Orders(504, "Biryani", 199.99);
		o4.setCustomer(c2);

		// session.persist(o1);
		// session.persist(o2);
		// session.persist(o3);
		// session.persist(o4);

		Customer c = new Customer(3, "Bharat");
		CustomerDetails cd = new CustomerDetails(103, "bharat@gmail.com", 9000000000L, "Samastipur");
		c.setCustomerDetails(cd);

		Orders o5 = new Orders(505, "Manchurian", 129.49);
		Orders o6 = new Orders(506, "Kheer", 249.99);

		o5.setCustomer(c);
		o6.setCustomer(c);
		
		// session.persist(o5);
		// session.persist(o6);
		
		
		// Read
		Orders order = session.get(Orders.class, 501);
		Customer customer = order.getCustomer();
		CustomerDetails customerDetails = customer.getCustomerDetails();
		
//		System.out.println(order);
//		System.out.println(customer);
//		System.out.println(customerDetails);
		
		/*
		Query query = session.createQuery("FROM Customer c");
		List list = query.getResultList();
		
		for (Object o : list) {
			System.out.println(o.toString());
		}
		*/
		
		Query<Customer> query = session.createQuery("FROM Customer c", Customer.class);
        List<Customer> customers = query.getResultList();
		
		if (customers != null) {
			for (Customer cus : customers) {
				System.out.println(cus.toString());
			}
		}
		
		
		// Delete
		if (order != null) {
			// session.remove(order);
		} else {
			System.out.println("Order not present with this id");
		}

		t.commit();
	}
}
