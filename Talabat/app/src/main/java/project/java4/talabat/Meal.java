package project.java4.talabat;

public class Meal {

    private int imageResource ;
    private float price ;
    private String name ;
    private String description ;
    private String Number;
    Meal(String name, float price, String description, int imageResource, String Number){
        this.name = name ;
        this.price=price ;
        this.description= description ;
        this.imageResource = imageResource ;
        this.Number=Number;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }
}
