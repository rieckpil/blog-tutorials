package de.rieckpil.blog.control;

import com.github.javafaker.Faker;
import de.rieckpil.blog.entity.Customer;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Startup
@Singleton
public class CustomerService {
    private List<Customer> customers;

    @PostConstruct
    public void init() {
        this.customers = createRandomCustomers();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void deleteCustomer(Customer customer) {
        this.customers.remove(customer);
    }

    private List<Customer> createRandomCustomers() {
        List<Customer> result = new ArrayList<>();
        Faker faker = new Faker();

        for (int i = 0; i < 100; i++) {
            result.add(new Customer(faker.name().firstName(),
                    faker.name().lastName(),
                    faker.idNumber().valid(),
                    ThreadLocalRandom.current().nextLong(1_000_000)));
        }
        return result;
    }
}
