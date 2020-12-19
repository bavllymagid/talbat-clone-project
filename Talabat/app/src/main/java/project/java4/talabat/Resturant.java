package project.java4.talabat;

public class Resturant {
    private int imageResource ;
    private String name ;
    private String number ;

    Resturant(int imageResource ,String name , String number){
        this.name = name ;
        this.number = number ;
        this.imageResource = imageResource ;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public int getImageResource() {
        return imageResource;
    }
}
