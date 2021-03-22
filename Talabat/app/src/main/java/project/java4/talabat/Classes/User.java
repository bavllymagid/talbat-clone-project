package project.java4.talabat.Classes;

public class User {
    private final  String name;
    private final String email;
    private String password;

    public User(String name, String email, String password ){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
