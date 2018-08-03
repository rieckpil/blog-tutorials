package de.rieckpil.blog.customers.control;

import de.rieckpil.blog.customers.entity.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CustomerManager {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Customer> loadAllCustomers() {
        return this.entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    public void delete(Customer customer) {
        if (entityManager.contains(customer)) {
            entityManager.remove(customer);
        } else {
            Customer managedCustomer = entityManager.find(Customer.class, customer.getId());
            if (managedCustomer != null) {
                entityManager.remove(managedCustomer);
            }
        }
    }
}
