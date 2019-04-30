package de.rieckpil.blog.control;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.github.javafaker.Faker;

import de.rieckpil.blog.entity.Customer;

@Singleton
@Startup
public class CustomerService {

	private List<Customer> customers;

	@PostConstruct
	public void init() {
		this.customers = createRandomCustomers();
	}

	@Schedule(hour = "*", minute = "*/10", persistent = false)
	public void resetCustomers() {
		this.customers = createRandomCustomers();
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void deleteCustomer(Customer customer) {
		this.customers.remove(customer);

		if (this.customers.isEmpty()) {
			this.customers = createRandomCustomers();
		}
	}

	public Customer findByCustomerId(String customerId) {
		return this.customers.stream().filter(c -> c.getCustomerId().equals(customerId)).findFirst().orElse(null);
	}

	private List<Customer> createRandomCustomers() {
		List<Customer> result = new ArrayList<>();

		for (int i = 0; i < 100; i++) {
			Faker faker = new Faker();
			result.add(new Customer(faker.name().firstName(), faker.name().lastName(), faker.idNumber().valid(),
					ThreadLocalRandom.current().nextLong(1_000_000)));
		}

		return result;
	}
}
