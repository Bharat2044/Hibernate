package com.bharat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class Launch {

	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure();
		
		cfg.addAnnotatedClass(Employee.class);
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		
		// SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Employee.class).buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		Employee emp1 = new Employee(1, "Bharat", 200000.99, "bharat@gmail.com", "IT");
		Employee emp2 = new Employee(2, "Deepak", 190000.99, "deepak@gmail.com", "Finance");
		Employee emp3 = new Employee(3, "Raj", 25000.99, "raj@gmail.com", "IT");
		Employee emp4 = new Employee(4, "Mike", 55555.49, "mike@gmail.com", "Marketing");
		Employee emp5 = new Employee(5, "Stalin", 22500.49, "stalin@gmail.com", "HR");

		// session.save(emp1);	// The method save(Object) from the type Session is deprecated
		session.persist(emp2);
		session.persist(emp3);
		session.persist(emp4);
		session.persist(emp5);
		
		Transaction t = session.beginTransaction();
		t.commit();
	}
}
