package de.rieckpil.blog.boundary;

import de.rieckpil.blog.control.CustomerService;
import de.rieckpil.blog.entity.Customer;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

@Named
@ViewScoped
public class CustomerListBean implements Serializable {

    private List<Customer> customers;
    private List<Customer> filteredCustomerList;
    private List<Customer> selectedCustomerList;

    @Inject
    private CustomerService customerService;

    @PostConstruct
    public void init() {
        customers = customerService.getCustomers();
    }

    public String getTotalRevenue() {
        if (this.customers == null) {
            return "0";
        }

        Long totalRevenue = customers.stream().mapToLong(Customer::getBilledRevenue).sum();
        return new DecimalFormat("###,###.###").format(totalRevenue);
    }

    public void deleteCustomers() {
        for (Customer customer : selectedCustomerList) {
            this.customerService.deleteCustomer(customer);

            if (filteredCustomerList != null) {
                this.filteredCustomerList.remove(customer);
            }

            this.customers = customerService.getCustomers();
        }
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Customer> getFilteredCustomerList() {
        return filteredCustomerList;
    }

    public void setFilteredCustomerList(List<Customer> filteredCustomerList) {
        this.filteredCustomerList = filteredCustomerList;
    }

    public List<Customer> getSelectedCustomerList() {
        return selectedCustomerList;
    }

    public void setSelectedCustomerList(List<Customer> selectedCustomerList) {
        this.selectedCustomerList = selectedCustomerList;
    }
}
