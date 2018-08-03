package de.rieckpil.blog.customers.boundary;

import de.rieckpil.blog.customers.control.CustomerManager;
import de.rieckpil.blog.customers.entity.Customer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class CustomersBacking {

    private List<Customer> customers;

    @Inject
    private CustomerManager customerManager;

    @PostConstruct
    public void init() {
        this.customers = customerManager.loadAllCustomers();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void delete(Customer customer) {
        System.out.println("Delete customer with ID: " + customer.getId());
        customerManager.delete(customer);
        customers.remove(customer);
    }

    public void delete() {
        System.out.println("Deleting everything");
    }

    public void buttonAction(ActionEvent actionEvent) {
        addMessage("Welcome to Primefaces!!");
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
