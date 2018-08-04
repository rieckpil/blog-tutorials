package de.rieckpil.blog.customers.boundary;

import de.rieckpil.blog.customers.control.CustomerManager;
import de.rieckpil.blog.customers.entity.Customer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

@Named
@RequestScoped
public class CustomersBacking {

    private List<Customer> customers;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String email;

    @Past
    private LocalDate dayOfBirth;

    @Inject
    private CustomerManager customerManager;

    @PostConstruct
    public void init() {
        this.customers = customerManager.loadAllCustomers();
    }

    public void delete(Customer customer) {
        System.out.println("Delete customer with ID: " + customer.getId());
        customerManager.delete(customer);
        customers.remove(customer);
    }

    public void add() {
        customerManager.addNewCustomer(firstName, lastName, email, dayOfBirth);
        this.customers = customerManager.loadAllCustomers();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(LocalDate dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }
}
