package distancematcher;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Haiyan
 *
 */
public class CustomerTest {

    /**
     * Test method for {@link distancematcher.Customer#compareTo(distancematcher.Customer)}.
     */
    @Test
    public void testCompareTo() {
        Customer customer1 = new Customer(1);
        Customer customer2 = new Customer(2);
        Customer customer3 = new Customer(3);

        List<Customer> customerList = Arrays.asList(customer2, customer3, customer1);
        List<Customer> customerListOrdered = Arrays.asList(customer1, customer2, customer3);

        Collections.sort(customerList);
        Assert.assertArrayEquals(customerListOrdered.toArray(), customerList.toArray());
    }

}
