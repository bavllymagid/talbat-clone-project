package project.java4.talabat;

public class Order {
    private String mealName;
    private int mealPrice;
    private int quantity;
    private String date;
    private byte[] image;
    private String restaurantName;
    private String email;

    public Order(String email, String mealName, int mealPrice, int quantity, String date, byte[] image, String restaurantName) {
        this.mealName = mealName;
        this.mealPrice = mealPrice;
        this.quantity = quantity;
        this.date = date;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * getters
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

    public int getQuantity() {
        return quantity;
    }

    public String getDate() {
        return date;
    }
}
