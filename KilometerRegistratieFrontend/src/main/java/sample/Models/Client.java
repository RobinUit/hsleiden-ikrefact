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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(int housenumber) {
        this.housenumber = housenumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
