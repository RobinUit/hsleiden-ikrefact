package com.DigitaleFactuur.models;

import javax.persistence.*;
import java.security.Principal;

@Entity
@Table(name = "declaration")
public class Declaration {

    @Id
    @MapKeyColumn
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int declarationID;

    private int ownerID;

    private String description;
    private double declaredKilometers;
    private double declaredCompensation;

    private String originZipcode;
    private String originHouseNumber;
    private String originStreet;
    private String originCity;
    private String originCountry;

    private String destinationZipcode;
    private String destinationHouseNumber;
    private String destinationStreet;
    private String destinationCity;
    private String destinationCountry;

    public Declaration(
            int ownerID,
            String description,
            double declaredKilometers,
            double declaredCompensation,
            String originZipcode,
            String originHouseNumber,
            String originStreet,
            String originCity,
            String originCountry,
            String destinationZipcode,
            String destinationHouseNumber,
            String destinationStreet,
            String destinationCity,
            String destinationCountry) {
        this.ownerID = ownerID;
        this.description = description;
        this.declaredKilometers = declaredKilometers;
        this.declaredCompensation = declaredCompensation;
        this.originZipcode = originZipcode;
        this.originHouseNumber = originHouseNumber;
        this.originStreet = originStreet;
        this.originCity = originCity;
        this.originCountry = originCountry;
        this.destinationZipcode = destinationZipcode;
        this.destinationHouseNumber = destinationHouseNumber;
        this.destinationStreet = destinationStreet;
        this.destinationCity = destinationCity;
        this.destinationCountry = destinationCountry;
    }

    public Declaration(
            int declarationID,
            int ownerID,
            String description,
            double declaredKilometers,
            double declaredCompensation,
            String originZipcode,
            String originHouseNumber,
            String originStreet,
            String originCity,
            String originCountry,
            String destinationZipcode,
            String destinationHouseNumber,
            String destinationStreet,
            String destinationCity,
            String destinationCountry) {
        this.declarationID = declarationID;
        this.ownerID = ownerID;
        this.description = description;
        this.declaredKilometers = declaredKilometers;
        this.declaredCompensation = declaredCompensation;
        this.originZipcode = originZipcode;
        this.originHouseNumber = originHouseNumber;
        this.originStreet = originStreet;
        this.originCity = originCity;
        this.originCountry = originCountry;
        this.destinationZipcode = destinationZipcode;
        this.destinationHouseNumber = destinationHouseNumber;
        this.destinationStreet = destinationStreet;
        this.destinationCity = destinationCity;
        this.destinationCountry = destinationCountry;
    }


    public int getDeclarationID() {
        return declarationID;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public String getDescription() {
        return description;
    }

    public double getDeclaredKilometers() {
        return declaredKilometers;
    }

    public double getDeclaredCompensation() {
        return declaredCompensation;
    }

    public String getOriginZipcode() {
        return originZipcode;
    }

    public String getOriginHouseNumber() {
        return originHouseNumber;
    }

    public String getOriginStreet() {
        return originStreet;
    }

    public String getOriginCity() {
        return originCity;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public String getDestinationZipcode() {
        return destinationZipcode;
    }

    public String getDestinationHouseNumber() {
        return destinationHouseNumber;
    }

    public String getDestinationStreet() {
        return destinationStreet;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }
}
