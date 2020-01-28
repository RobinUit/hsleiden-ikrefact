
package com.DigitaleFactuur.models;

import javax.persistence.*;
import java.security.Principal;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @MapKeyColumn
    private String licensePlate;
    private int ownerID;
    private String name;
    private String brand;
    private String type;
    private String color;
    private String fuelType;

    public Car(String licensePlate,int ownerID, String name, String brand, String type, String color, String fuelType){
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

    public int getOwnerID() {
        return ownerID;
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