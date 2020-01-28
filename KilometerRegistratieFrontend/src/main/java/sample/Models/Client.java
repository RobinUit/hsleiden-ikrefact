package sample.Models;

public class Client {

    private int ownerID;
    private String name;
    private String zipcode;
    private int housenumber;
    private String street;
    private String city;
    private String country;

    public Client( int ownerID,
                   String name,
                   String zipcode,
                   int housenumber,
                   String street,
                   String city,
                   String country) {
        this.ownerID = ownerID;
        this.name = name;
        this.zipcode = zipcode;
        this.housenumber = housenumber;
        this.street = street;
        this.city = city;
        this.country = country;
    }
}
