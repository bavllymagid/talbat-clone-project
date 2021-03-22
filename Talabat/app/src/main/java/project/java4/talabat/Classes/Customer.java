package project.java4.talabat.Classes;

public class Customer extends User {
    private final String phoneNumber;
    private final String address;


    public Customer(String name, String email, String password, String phoneNumber, String address) {
        super(name, email, password);
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
