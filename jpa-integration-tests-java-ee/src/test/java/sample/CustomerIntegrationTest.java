package sample;

import org.junit.Rule;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class CustomerIntegrationTest {

    @Rule
    public EntityManagerProvider provider = EntityManagerProvider.withUnit("integration-test");

    @Test
    public void testSavingNewCustomer() {
        this.provider.begin();
        Customer sampleCustomer = new Customer("John", "Duke", LocalDate.of(2000, 12, 12));
        this.provider.em().persist(sampleCustomer);

        List<Customer> resultList = this.provider.em().createQuery("SELECT c FROM Customer c", Customer.class).getResultList();

        for (Customer c : resultList) {
            System.out.println("c = " + c);
        }

        this.provider.commit();
    }

}