package project.java4.talabat.Classes;

public class Restaurant {
    private byte[] imageResource;
    private String name;


    public Restaurant(byte[] imageResource, String name) {
        this.name = name;
        this.imageResource = imageResource;

    }

    /** getters
     */
    public String getName() {
        return name;
    }
    public byte[] getImageResource() {
        return imageResource;
    }
}
