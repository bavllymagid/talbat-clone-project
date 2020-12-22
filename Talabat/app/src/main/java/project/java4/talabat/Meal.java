package project.java4.talabat;
public class Meal {

    private int imageResource ;
    private String price ;
    private String name ;
    private String description ;
    private String Number;
    Meal(int imageResource,String name, String price, String description,String Number){
        this.imageResource=imageResource;
        this.name = name ;
        this.price=price ;
        this.description= description ;
        this.Number=Number;
    }
    public int getImageResource() {
        return imageResource;
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

