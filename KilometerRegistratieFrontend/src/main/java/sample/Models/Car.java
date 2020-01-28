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

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public String getFuelType() {
        return fuelType;
    }
}