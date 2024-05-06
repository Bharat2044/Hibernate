package com.hibernate.crud;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

public class Launch {
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure();
		
		cfg.addAnnotatedClass(Employee.class);
		
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction t = session.beginTransaction();
		
		// Create
		Employee emp = new Employee(3, "Bharat", 212550.99, "bharat@gmail.com", "IT");
		
		// session.save(emp);  // The method save(Object) from the type Session is deprecated
		// session.persist(emp);
		
		
		// Read
		int id = 6;
		// Employee e = session.load(Employee.class, id);// The method load() from the type Session is deprecated
		Employee e = session.get(Employee.class, id);
		// System.out.println(employee);
		if(e != null) {
            // System.out.println(e.getId() + "  " + e.getName() + "  " + e.getSalary() + "  " + e.getEmail() + "  " + e.getDept());
            System.out.println(e);
		} else {
            System.out.println("Employee with ID " + id + " not found.");
        }
		
		/*
		// Read All Employees
		Query query = session.createQuery("FROM Employee e");
		List list = query.getResultList();
		
		for (Object o : list) {
			System.out.println(o.toString());
		}
		*/
		
		Query<Employee> query = session.createQuery("FROM Employee e", Employee.class);
        List<Employee> employees = query.getResultList();

        if (!employees.isEmpty()) {
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        } else {
            System.out.println("No employees found.");
        }
        
		
		// Update
		if(e != null) {
			e.setName("Advika");
			e.setSalary(48008.99);
			e.setEmail("advika@gmail.com");
			
			// session.update(e);  // The method update(Object) from the type Session is deprecated
			session.merge(e);
		} else {
	        System.out.println("Employee with ID " + id + " not found.");
	    }
	
	
		// Delete
		if(e != null) {
			// session.delete(e);  // The method delete(Object) from the type Session is deprecated
			session.remove(e);
		} else {
            System.out.println("Employee with ID " + id + " not found.");
        }
		
		
		// Update salary by 15000 whose salary is more than 30000
		// Query update = session.createQuery("UPDATE Employee e SET e.salary = e.salary + 15000 WHERE e.salary > 30000");
		MutationQuery update = session.createMutationQuery("UPDATE Employee e SET e.salary = e.salary + 15000 WHERE e.salary > 30000");
        int updatedCount = update.executeUpdate();
        System.out.println("Updated " + updatedCount + " employee(s) salary.");

        
        // Delete employee whose department is IT
        // Query delete = session.createQuery("DELETE FROM Employee e WHERE e.dept = 'HR'");
        MutationQuery delete = session.createMutationQuery("DELETE FROM Employee e WHERE e.dept = 'Finance'");
        int deleteCount = delete.executeUpdate();
        System.out.println("Deleted " + deleteCount + " employee(s).");

		t.commit();
	}
}
