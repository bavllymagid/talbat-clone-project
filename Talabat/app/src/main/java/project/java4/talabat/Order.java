package project.java4.talabat;

public class Order{
    private String mealName;
    private int mealPrice;
    private byte[] image;
    private String restaurantName;
    private String email;

    public Order(String email, String mealName, int mealPrice, byte[] image, String restaurantName) {
        this.mealName = mealName;
        this.mealPrice = mealPrice;
        this.image = image;
        this.restaurantName = restaurantName;
        this.email = email;
    }

    /**
     * setters
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /** getters
     */
    public String getRestaurantName() {
        return restaurantName;
    }

    public byte[] getImage() {
        return image;
    }

    public int getMealPrice() {
        return mealPrice;
    }

    public String getMealName() {
        return mealName;
    }

    public String getEmail() {
        return email;
    }
}
