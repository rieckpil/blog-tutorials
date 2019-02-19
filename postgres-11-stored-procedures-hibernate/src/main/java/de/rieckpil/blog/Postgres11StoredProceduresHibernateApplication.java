package de.rieckpil.blog;

import java.sql.CallableStatement;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class Postgres11StoredProceduresHibernateApplication implements CommandLineRunner {

	@Autowired
	private EntityManager em;

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(Postgres11StoredProceduresHibernateApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

//		StoredProcedureQuery storedProcedureQuery = this.em.createNamedStoredProcedureQuery("raiseWage");
//		storedProcedureQuery.setParameter("operating_years", 20);
//		storedProcedureQuery.setParameter("raise", 1000);
//		storedProcedureQuery.execute();

		Statement st = dataSource.getConnection().createStatement();
		st.executeQuery("CALL p_raise_wage_employee_older_than(?,?)");

		CallableStatement prepareCall = dataSource.getConnection()
				.prepareCall("{call p_raise_wage_employee_older_than(?,?)}");
		prepareCall.setInt(1, 20);
		prepareCall.setInt(2, 100);
		prepareCall.executeUpdate();

		List<Employee> employees = this.em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();

		for (Employee employee : employees) {
			System.out.println(employee);
		}

	}

}
