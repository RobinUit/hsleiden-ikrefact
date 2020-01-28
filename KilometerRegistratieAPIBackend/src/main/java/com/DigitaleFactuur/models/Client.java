package com.DigitaleFactuur.models;

import javax.persistence.*;
import java.security.Principal;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @MapKeyColumn
    private int clientID;

    private int ownerID;
    private String name;
    private String zipcode;
    private int housenumber;
    private String street;
    private String city;
    private String country;

    public Client(int ownerID, String name, String postalCode, int houseNumber, String street, String city, String country) {
        this.ownerID = ownerID;
        this.name = name;
        this.zipcode = postalCode;
        this.housenumber = houseNumber;
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public int getClientID() {
        return clientID;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public String getName() {
        return name;
    }

    public String getZipcode() {
        return zipcode;
    }

    public int getHousenumber() {
        return housenumber;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}


