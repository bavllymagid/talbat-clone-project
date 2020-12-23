package project.java4.talabat;

public class Resturant {
    private static int mealIndex = 0;
    private int imageResource ;
    private String name ;
    private String number ;

    Resturant(int imageResource ,String name , String number){
        this.name = name ;
        this.number = number ;
        this.imageResource = imageResource ;
    }
    //New Meal For Restaurant

   /* Meal Meals[];

    public void createMeal (Meal m){
        while (true){
            String s = Meals[mealIndex].getName();
            if (s != null){mealIndex++;}
            else {break;}
        }
        Meals[mealIndex]=m;
    }
    //Remove A Meal
    public void removeMeal (int oldMealIndex){
        Meals[oldMealIndex].setName(null);
    }*/


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
