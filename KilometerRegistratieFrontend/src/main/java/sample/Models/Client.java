package sample.Models;

public class Client {

    private int ownerID;
    private String clientName;
    private String clientPostalCode;
    private int clientHouseNumber;
    private String clientCity;
    private String clientCountry;

    public Client(String name, String postalCode, int houseNumber, String city, String country) {
        this.clientName = name;
        this.clientPostalCode = postalCode;
        this.clientHouseNumber = houseNumber;
        this.clientCity = city;
        this.clientCountry = country;
    }

    public Client( int ownerID, String name, String postalCode, int houseNumber, String city, String country) {
        this.ownerID = ownerID;
        this.clientName = name;
        this.clientPostalCode = postalCode;
        this.clientHouseNumber = houseNumber;
        this.clientCity = city;
        this.clientCountry = country;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getOwnerID(){return ownerID;}
    public void setOwnerID(int ownerID){
        this.ownerID = ownerID;
    }

    public String getClientPostalCode() {
        return clientPostalCode;
    }

    public void setClientPostalCode(String clientPostalCode) {
        this.clientPostalCode = clientPostalCode;
    }

    public int getClientHouseNumber() {
        return clientHouseNumber;
    }

    public void setClientHouseNumber(int clientHouseNumber) {
        this.clientHouseNumber = clientHouseNumber;
    }

    public String getClientCity() {
        return clientCity;
    }

    public void setClientCity(String clientCity) {
        this.clientCity = clientCity;
    }

    public String getClientCountry() {
        return clientCountry;
    }

    public void setClientCountry(String clientCountry) {
        this.clientCountry = clientCountry;
    }
}
