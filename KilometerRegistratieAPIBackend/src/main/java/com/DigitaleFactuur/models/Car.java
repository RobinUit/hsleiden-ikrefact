
package com.DigitaleFactuur.models;

import javax.persistence.*;
import java.security.Principal;

@Entity
@Table(name = "car")
public class Car implements Principal {

    @Id
    @MapKeyColumn
    private String licencePlate;
    private int ownerID;
    private String carName;
    private String carBrand;
    private String carType;
    private String carColor;
    private String fuelType;

    public Car(String licencePlate, String carName, String carBrand, String carType, String carColor, String fuelType){
        this.licencePlate = licencePlate;
        this.carName = carName;
        this.carBrand = carBrand;
        this.carType = carType;
        this.carColor = carColor;
        this.fuelType = fuelType;
    }

    //deze constructor heeft wel ownerID bij zich.
    public Car(String licencePlate,int ownerID, String carName, String carBrand, String carType, String carColor, String fuelType){
        this.licencePlate = licencePlate;
        this.ownerID = ownerID;
        this.carName = carName;
        this.carBrand = carBrand;
        this.carType = carType;
        this.carColor = carColor;
        this.fuelType = fuelType;
    }

    public String getLicensePlate() {
        return licencePlate;
    }
    public int getOwnerID(){return ownerID;}
    public void setOwnerID(int ownerID){
        this.ownerID = ownerID;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String getName() {
        return carName;
    }
}