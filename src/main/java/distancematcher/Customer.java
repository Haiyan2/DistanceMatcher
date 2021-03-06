package distancematcher;

/**
 * 
 * Customer class.
 *
 */
class Customer implements Comparable<Customer> {
    private int userId;
    private String name;
    private float latitude;
    private float longitude;

    Customer(int userId) {
        this.userId = userId;
    }

    Customer(int userId, String name, float latitude, float longitude) {
        this.userId = userId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     * Provide the default sort of Customer by user ID.
     */
    @Override
    public int compareTo(Customer o) {
        return Integer.valueOf(userId).compareTo(Integer.valueOf(o.userId));
    }

}
