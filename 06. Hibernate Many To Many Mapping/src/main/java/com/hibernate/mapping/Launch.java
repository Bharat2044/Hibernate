package com.hibernate.mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Launch {
	public static void main(String[] args) {

		Configuration cfg = new Configuration();
		cfg.configure();
  
		cfg.addAnnotatedClass(Employee.class);
		cfg.addAnnotatedClass(Project.class);

		try (Session session = cfg.buildSessionFactory().openSession()) {
			Transaction t = session.beginTransaction();

			// CRUD Operations
			Employee emp1 = new Employee(1, "Bharat");
			Employee emp2 = new Employee(2, "Raj");

			Project pro1 = new Project(1, "Google Docs");
			Project pro2 = new Project(2, "Google Slides");

			// Create
			ArrayList<Project> projects = new ArrayList<>();
			projects.add(pro1);
			projects.add(pro2);

			emp1.setProjects(projects);
			emp2.setProjects(projects);

//			session.persist(emp1);
//			session.persist(emp2);
//			session.persist(pro1);
//			session.persist(pro2);

			// Read
			int id1 = 2;
			// Project project = session.load(Project.class, id1);
			Project project = session.get(Project.class, id1);
			List<Employee> employees = project.getEmployees();

			System.out.println(project);
			for (Employee emp : employees) {
				System.out.println(emp.toString());
			}

			int id2 = 1;
			Employee employee = session.get(Employee.class, id2);
			List<Project> projects2 = employee.getProjects();

			System.out.println(employee.toString());

			// Iterator<Project> itr = projects2.iterator();
			ListIterator<Project> itr = projects2.listIterator();
			while (itr.hasNext()) {
				System.out.println(itr.next());
			}
			

			// Delete
			if (employee != null) {
				// session.remove(employee);
			} else {
				System.out.println("Employee Not Available!!");
			}

			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
}
