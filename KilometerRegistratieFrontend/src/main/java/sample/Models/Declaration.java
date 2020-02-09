package sample.Models;

public class Declaration {

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

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDeclaredKilometers() {
        return declaredKilometers;
    }

    public void setDeclaredKilometers(double declaredKilometers) {
        this.declaredKilometers = declaredKilometers;
    }

    public double getDeclaredCompensation() {
        return declaredCompensation;
    }

    public void setDeclaredCompensation(double declaredCompensation) {
        this.declaredCompensation = declaredCompensation;
    }

    public String getOriginZipcode() {
        return originZipcode;
    }

    public void setOriginZipcode(String originZipcode) {
        this.originZipcode = originZipcode;
    }

    public String getOriginHouseNumber() {
        return originHouseNumber;
    }

    public void setOriginHouseNumber(String originHouseNumber) {
        this.originHouseNumber = originHouseNumber;
    }

    public String getOriginStreet() {
        return originStreet;
    }

    public void setOriginStreet(String originStreet) {
        this.originStreet = originStreet;
    }

    public String getOriginCity() {
        return originCity;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getDestinationZipcode() {
        return destinationZipcode;
    }

    public void setDestinationZipcode(String destinationZipcode) {
        this.destinationZipcode = destinationZipcode;
    }

    public String getDestinationHouseNumber() {
        return destinationHouseNumber;
    }

    public void setDestinationHouseNumber(String destinationHouseNumber) {
        this.destinationHouseNumber = destinationHouseNumber;
    }

    public String getDestinationStreet() {
        return destinationStreet;
    }

    public void setDestinationStreet(String destinationStreet) {
        this.destinationStreet = destinationStreet;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }
}