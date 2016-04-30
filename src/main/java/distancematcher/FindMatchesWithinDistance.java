package distancematcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * This class reads a JSON file with customer information and print out all the
 * customers that are within a certain distance from a location, sorted by user
 * id.
 * 
 * To execute this class from Maven, use the command:
 * <code>mvn exec:java -Dexec.mainClass="distancematcher.FindMatchesWithinDistance"</code>
 *
 */
public class FindMatchesWithinDistance {

    final static Logger LOGGER = LoggerFactory.getLogger(FindMatchesWithinDistance.class);

    /**
     * Destination Latitude.
     */
    public static final float DESTINATION_LATITUDE = 53.3381985f;

    /**
     * Destination Longitude.
     */
    public static final float DESTINATION_LONGITUDE = -6.2592576f;

    /**
     * Distance limit with unit in km.
     */
    public static final float DISTANCE_THRESHOLD = 100f;

    /**
     * Entry point of the project.
     * 
     * @param args
     *            the input file path.
     * @throws IOException
     *             throws IOException when reading the file if any.
     */
    public static void main(String... args) throws IOException {

        FindMatchesWithinDistance findMatchesWithinDistance = new FindMatchesWithinDistance();

        // Convert the input file into a list of strings.
        List<String> inputLines = findMatchesWithinDistance.readFromInputFile("customers.json");
        if (inputLines.size() == 0) {
            LOGGER.error("No record exists in the input file.");
            return;
        }

        // Build a list of customer information from the list of strings.
        List<Customer> customerList = findMatchesWithinDistance.buildCustomerListFromInput(inputLines);
        if (customerList.size() == 0) {
            LOGGER.error("No valid customer record exists.");
            return;
        }

        // Build a list of customer information that are within a specified
        // distance.
        List<Customer> customerInviteList = findMatchesWithinDistance.getMatchListWithinDistance(customerList,
                DESTINATION_LATITUDE, DESTINATION_LONGITUDE, DISTANCE_THRESHOLD);
        if (customerInviteList.size() == 0) {
            LOGGER.error("No customer record within the specified distance {} exists.", DISTANCE_THRESHOLD);
            return;
        }

        // Print out the list of customer information in format {ID, Name}
        for (Customer customer : customerInviteList) {
            System.out.println(customer.getUserId() + ", " + customer.getName());
        }
    }

    /**
     * Read from input files and return a list of strings for each line.
     * 
     * @param filePath
     *            the input file path.
     * @return a list of strings.
     * @throws IOException
     *             throws IOException when reading the file if any.
     */
    private List<String> readFromInputFile(String filePath) throws IOException {
        List<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String s;
            while ((s = reader.readLine()) != null) {
                data.add(s);
            }
        }

        return data;
    }

    /**
     * Build the list of customers.
     * 
     * @param data
     *            the input list of strings.
     * @return the list of customers.
     */
    public List<Customer> buildCustomerListFromInput(List<String> data) {

        // Message pattern of each line in the file.
        final Pattern MESSAGE_PATTERN = Pattern.compile("^\\s*\\{\\s*\"latitude\"\\s*:\\s*\"([^\"]*)\"\\s*"
                + ",\\s*\"user_id\"\\s*:\\s*([^,]*)\\s*" + ",\\s*\"name\"\\s*:\\s*\"([^\"]*)\"\\s*"
                + ",\\s*\"longitude\"\\s*:\\s*\"([^\"]*)\"\\s*\\}\\s*$");

        List<Customer> customerList = new ArrayList<>();

        for (String s : data) {

            Matcher m = MESSAGE_PATTERN.matcher(s);
            if (m.matches()) {

                float latitude = Float.parseFloat(m.group(1));
                int user_id = Integer.parseInt(m.group(2));
                String name = m.group(3);
                float longitude = Float.parseFloat(m.group(4));

                customerList.add(new Customer(user_id, name, latitude, longitude));
            }
        }
        return customerList;
    }

    /**
     * Get the list of customers that are within the specified distance to the
     * destination. The customers are ordered by user id ascending.
     * 
     * @param customerList
     *            list of input customers.
     * @param destinationLatitude
     *            Latitude of the destination
     * @param destinationLongitude
     *            Longitude of the destination
     * @param distanceWithin
     *            the specified distance limit
     * @return the list of customers that are within the specified distance
     *         ordered by user id ascending.
     */
    private List<Customer> getMatchListWithinDistance(List<Customer> customerList, final float destinationLatitude,
            final float destinationLongitude, final float distanceWithin) {

        List<Customer> customerInviteList = new ArrayList<>();

        for (Customer customer : customerList) {
            float distance = Utilities.calculateDistance(customer.getLatitude(), customer.getLongitude(),
                    destinationLatitude, destinationLongitude);

            if (distance < distanceWithin) {
                customerInviteList.add(customer);
            }
        }

        // Sort the list of customer information by ID with ascending order.
        Collections.sort(customerInviteList);

        return customerInviteList;
    }

}
