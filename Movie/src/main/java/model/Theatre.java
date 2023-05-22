package model;


public class Theatre {

    private String id;
    private String name;
    private String Address;
    private String place;
    private  String state;
    private String pin;

    public Theatre(String id, String name, String address, String place, String state, String pin) {
        this.id = id;
        this.name = name;
        Address = address;
        this.place = place;
        this.state = state;
        this.pin = pin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
