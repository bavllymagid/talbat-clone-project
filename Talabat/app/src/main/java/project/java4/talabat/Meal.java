package project.java4.talabat;


public class Meal extends Resturant{

    private int id;
    private String mealName;
    private String mealDescription;
    private int mealPrice;
    private byte[] image;
    private String restaurantName;


    public Meal(int id, String mealName, String mealDescription, int mealPrice, byte[] image) {
        super(image, "");
        this.id = id;
        this.mealName = mealName;
        this.mealDescription = mealDescription;
        this.mealPrice = mealPrice;
        this.image = image;
    }


    public Meal(String mealName, String mealDescription, int mealPrice, byte[] image, String restaurantName) {
        super(image, restaurantName);
        this.mealName = mealName;
        this.mealDescription = mealDescription;
        this.mealPrice = mealPrice;
        this.image = image;
        this.restaurantName = restaurantName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getMealName() {
        return mealName;
    }

    public String getMealDescription() {
        return mealDescription;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public void setMealDescription(String mealDescription) {
        this.mealDescription = mealDescription;
    }

    public int getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(int mealPrice) {
        this.mealPrice = mealPrice;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


}
