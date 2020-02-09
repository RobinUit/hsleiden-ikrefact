package sample.Models;

public class Car {
    private String licensePlate;
    private int ownerID;
    private String name;
    private String brand;
    private String type;
    private String color;
    private String fuelType;

    public Car(String licensePlate,
               int ownerID,
               String name,
               String brand,
               String type,
               String color,
               String fuelType) {
        this.licensePlate = licensePlate;
        this.ownerID = ownerID;
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.color = color;
        this.fuelType = fuelType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}