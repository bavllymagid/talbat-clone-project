package project.java4.talabat;


public class Meal {

    private int id;
    private String mealName;
    private String mealDescription;
    private int mealPrice;
    private byte[] image;
    private String restaurantName;

    /**
     * for updating meals
     */
    public Meal(int id, String mealName, String mealDescription, int mealPrice, byte[] image) {
        this.id = id;
        this.mealName = mealName;
        this.mealDescription = mealDescription;
        this.mealPrice = mealPrice;
        this.image = image;
    }

    /**
     * for declaring meals
     */
    public Meal(String mealName, String mealDescription, int mealPrice, byte[] image, String restaurantName) {
        this.mealName = mealName;
        this.mealDescription = mealDescription;
        this.mealPrice = mealPrice;
        this.image = image;
        this.restaurantName = restaurantName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    /**
     * setters
     */
    public void setId(int id) {
        this.id = id;
    }

    public void setMealPrice(int mealPrice) {
        this.mealPrice = mealPrice;
    }

    /**
     * getters
     */
    public int getId() {
        return id;
    }

    public String getMealName() {
        return mealName;
    }

    public String getMealDescription() {
        return mealDescription;
    }

    public int getMealPrice() {
        return mealPrice;
    }

    public byte[] getImage() {
        return image;
    }


}
