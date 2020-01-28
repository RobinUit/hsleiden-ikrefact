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

    public String getDestinationZipcode() {
        return destinationZipcode;
    }
}