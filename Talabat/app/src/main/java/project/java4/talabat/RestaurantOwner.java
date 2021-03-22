package project.java4.talabat;

public class RestaurantOwner extends User {
    String restaurantName;

    public RestaurantOwner(String name, String email, String password, String restaurantName) {
        super(name, email, password);
        this.restaurantName = restaurantName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }
}
