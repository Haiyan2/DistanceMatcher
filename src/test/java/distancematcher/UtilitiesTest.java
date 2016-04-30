package distancematcher;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Haiyan
 *
 */
public class UtilitiesTest {

    /**
     * Test method for
     * {@link distancematcher.Utilities#calculateDistance(float, float, float, float)}
     * .
     */
    @Test
    public void testCalculateDistance() {
        final float destinationLatitude = 53.3381985f;
        final float destinationLongitude = -6.2592576f;

        // test case: two points are identical.
        float fromLatitude = destinationLatitude;
        float fromLongitude = destinationLongitude;
        float distance = Utilities.calculateDistance(fromLatitude, fromLongitude, destinationLatitude,
                destinationLongitude);
        assertEquals(0, distance, 0.01);

        // test case: distance between two known points.
        fromLatitude = 53.3291108f;
        fromLongitude = -6.2585054f;
        distance = Utilities.calculateDistance(fromLatitude, fromLongitude, destinationLatitude, destinationLongitude);
        assertEquals(1.01, distance, 0.01);
    }

}
