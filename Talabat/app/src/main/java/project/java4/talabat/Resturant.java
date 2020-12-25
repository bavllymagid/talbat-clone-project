package project.java4.talabat;

public class Resturant {
    private byte[] imageResource ;
    private String name ;


    Resturant(byte[] imageResource ,String name){
        this.name = name ;
        this.imageResource = imageResource ;

    }


    public String getName() {
        return name;
    }


    public byte[] getImageResource() {
        return imageResource;
    }
}
