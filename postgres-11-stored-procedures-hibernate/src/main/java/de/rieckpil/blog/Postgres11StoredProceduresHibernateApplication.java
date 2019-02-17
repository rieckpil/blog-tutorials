package de.rieckpil.blog;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class Postgres11StoredProceduresHibernateApplication implements CommandLineRunner {

	@Autowired
	private EntityManager em;

	public static void main(String[] args) {
		SpringApplication.run(Postgres11StoredProceduresHibernateApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		List<Employee> employees = this.em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();

		for (Employee employee : employees) {
			System.out.println(employee);
		}

		this.em.createNamedStoredProcedureQuery("raiseWage").setParameter("operating_years", 20)
				.setParameter("raise", 1000).execute();

		employees = this.em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();

		for (Employee employee : employees) {
			System.out.println(employee);
		}

	}

}
