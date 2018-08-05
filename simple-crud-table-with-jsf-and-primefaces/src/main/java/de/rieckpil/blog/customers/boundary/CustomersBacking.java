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

    private Customer customer = new Customer();

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
        customerManager.addNewCustomer(customer);
        this.customers = customerManager.loadAllCustomers();
        this.customer = new Customer();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustomerManager getCustomerManager() {
        return customerManager;
    }

    public void setCustomerManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }
}
