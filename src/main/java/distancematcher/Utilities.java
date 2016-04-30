package distancematcher;

/**
 * Utility class.
 */
abstract class Utilities {

    // Mean Radius of earth, with unit in km
    static final float MEAN_RADIUS = 6371.0088f;

    /**
     * Calculate the distance (in km) between two points with inputs coordinates in
     * degrees.
     * 
     * @param fromLatitude
     *            latitude of the form point
     * @param fromLongitude
     *            longitude of the from point
     * @param toLatitude
     *            latitude of the destination point
     * @param toLongitude
     *            longitude of the destination point
     * @return the distance between two points in km.
     */
    public static float calculateDistance(float fromLatitude, float fromLongitude, float toLatitude, float toLongitude) {

        double fromLatitudeRadian = Math.toRadians(fromLatitude);
        double fromLongitudeRadian = Math.toRadians(fromLongitude);
        double toLatitudeRadian = Math.toRadians(toLatitude);
        double toLongitudeRadian = Math.toRadians(toLongitude);

        double tmp = Math.acos(Math.sin(fromLatitudeRadian) * Math.sin(toLatitudeRadian) + Math.cos(fromLatitudeRadian)
                * Math.cos(toLatitudeRadian) * Math.cos(Math.abs(fromLongitudeRadian - toLongitudeRadian)));

        return (float) tmp * MEAN_RADIUS;
    }
}