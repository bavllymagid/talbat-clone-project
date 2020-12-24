package project.java4.talabat;


public class Meal {

    private int id;
    private String mealName;
    private String mealDescription;
    private int mealPrice;
    private byte[] image;


    public Meal(int id, String mealName, String mealDescription, int mealPrice, byte[] image) {
        this.id = id;
        this.mealName = mealName;
        this.mealDescription = mealDescription;
        this.mealPrice = mealPrice;
        this.image = image;
    }


    public Meal(String mealName, String mealDescription, int mealPrice, byte[] image) {
        this.mealName = mealName;
        this.mealDescription = mealDescription;
        this.mealPrice = mealPrice;
        this.image = image;


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
