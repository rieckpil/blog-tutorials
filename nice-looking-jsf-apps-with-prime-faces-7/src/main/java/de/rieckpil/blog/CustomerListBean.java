package de.rieckpil.blog;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.github.javafaker.Faker;

@Named
@ViewScoped
public class CustomerListBean implements Serializable {

	private static final long serialVersionUID = 4773746274170179581L;

	private List<Customer> customers;
	private List<Customer> filteredCustomerList;
	private List<Customer> selectedCustomerList;

	@PostConstruct
	public void init() {

		customers = new ArrayList<>();

		for (int i = 0; i < 100; i++) {
			Faker faker = new Faker();
			customers.add(new Customer(faker.name().firstName(), faker.name().lastName(), faker.idNumber().valid(),
					ThreadLocalRandom.current().nextLong(1_000_000)));
		}

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
			this.customers.remove(customer);

			if (filteredCustomerList != null) {
				this.filteredCustomerList.remove(customer);
			}
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
