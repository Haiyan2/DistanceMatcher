package distancematcher;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Haiyan
 *
 */
public class FindMatchesWithinDistanceTest {

    /**
     * Test method for
     * {@link distancematcher.FindMatchesWithinDistance#buildCustomerListFromInput(java.util.List)}
     * .
     */
    @Test
    public void testBuildCustomerListFromInput() {
        FindMatchesWithinDistance findMatchesWithinDistance = new FindMatchesWithinDistance();

        // String with invalid format
        String str1 = "{\"name\": \"Juli Bot\", -6.043701}";

        // String with valid formats
        String str2 = "{\"latitude\": \"51.92893\", \"user_id\": 1, \"name\": \"Alice Nic\", \"longitude\": \"-10.27699\"}";
        String str3 = "{\"latitude\": \"52.986375\", \"user_id\": 12, \"name\": \"Chris Lee\", \"longitude\": \"-6.043701\"}";

        List<String> data = Arrays.asList(str1, str2, str3);
        List<Customer> customerList = findMatchesWithinDistance.buildCustomerListFromInput(data);
        assertEquals(2, customerList.size());

        List<String> names = Arrays.asList(new String[] { "Alice Nic", "Chris Lee" });
        for (int i = 0; i < customerList.size(); i++) {
            assertEquals(names.get(i), customerList.get(i).getName());
        }
    }

}
