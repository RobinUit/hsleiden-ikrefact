package sample.Models;

public class Declaration {

    //private int declarationID;
    private int ownerID;
    private String decDesc;
    //private LocalDate decDate;
    private double decKilometers;
    private double decDeclaration;

    //Begin adres
    private String decBeginPostal;
    private String decBeginHouseNumber;
    private String decBeginStreet;
    private String decBeginCity;
    private String decBeginCountry;
    //Eind adres
    private String decEndPostal;
    private String decEndHouseNumber;
    private String decEndStreet;
    private String decEndCity;
    private String decEndCountry;

    public Declaration(int ownerID, String decDesc, double decKilometers, double decDeclaration, String decBeginPostal, String decBeginHouseNumber, String decBeginStreet, String decBeginCity, String decBeginCountry, String decEndPostal, String decEndHouseNumber, String decEndStreet, String decEndCity, String decEndCountry) {
        this.ownerID = ownerID;
        this.decDesc = decDesc;
        this.decKilometers = decKilometers;
        this.decDeclaration = decDeclaration;
        this.decBeginPostal = decBeginPostal;
        this.decBeginHouseNumber = decBeginHouseNumber;
        this.decBeginStreet = decBeginStreet;
        this.decBeginCity = decBeginCity;
        this.decBeginCountry = decBeginCountry;
        this.decEndPostal = decEndPostal;
        this.decEndHouseNumber = decEndHouseNumber;
        this.decEndStreet = decEndStreet;
        this.decEndCity = decEndCity;
        this.decEndCountry = decEndCountry;
    }
/*
    public Declaration(int declarationID, int ownerID, String decDesc, double decKilometers, double decDeclaration, String decBeginPostal, String decBeginHouseNumber, String decBeginStreet, String decBeginCity, String decBeginCountry, String decEndPostal, String decEndHouseNumber, String decEndStreet, String decEndCity, String decEndCountry) {
        this.declarationID = declarationID;
        this.ownerID = ownerID;
        this.decDesc = decDesc;
        this.decKilometers = decKilometers;
        this.decDeclaration = decDeclaration;
        this.decBeginPostal = decBeginPostal;
        this.decBeginHouseNumber = decBeginHouseNumber;
        this.decBeginStreet = decBeginStreet;
        this.decBeginCity = decBeginCity;
        this.decBeginCountry = decBeginCountry;
        this.decEndPostal = decEndPostal;
        this.decEndHouseNumber = decEndHouseNumber;
        this.decEndStreet = decEndStreet;
        this.decEndCity = decEndCity;
        this.decEndCountry = decEndCountry;
    }
 */




    public String getDecDesc() {
        return decDesc;
    }

    public void setDecDesc(String decDesc) {
        this.decDesc = decDesc;
    }

    public double getDecKilometers() {
        return decKilometers;
    }

    public void setDecKilometers(int decKilometers) {
        this.decKilometers = decKilometers;
    }

    public double getDecDeclaration() {
        return decDeclaration;
    }

    public void setDecDeclaration(double decDeclaration) {
        this.decDeclaration = decDeclaration;
    }

    public String getDecBeginPostal() {
        return decBeginPostal;
    }

    public void setDecBeginPostal(String decBeginPostal) {
        this.decBeginPostal = decBeginPostal;
    }

    public String getDecBeginHouseNumber() {
        return decBeginHouseNumber;
    }

    public void setDecBeginHouseNumber(String decBeginHouseNumber) {
        this.decBeginHouseNumber = decBeginHouseNumber;
    }

    public String getDecBeginStreet() {
        return decBeginStreet;
    }

    public void setDecBeginStreet(String decBeginStreet) {
        this.decBeginStreet = decBeginStreet;
    }

    public String getDecBeginCity() {
        return decBeginCity;
    }

    public void setDecBeginCity(String decBeginCity) {
        this.decBeginCity = decBeginCity;
    }

    public String getDecBeginCountry() {
        return decBeginCountry;
    }

    public void setDecBeginCountry(String decBeginCountry) {
        this.decBeginCountry = decBeginCountry;
    }

    public String getDecEndPostal() {
        return decEndPostal;
    }

    public void setDecEndPostal(String decEndPostal) {
        this.decEndPostal = decEndPostal;
    }

    public String getDecEndHouseNumber() {
        return decEndHouseNumber;
    }

    public void setDecEndHouseNumber(String decEndHouseNumber) {
        this.decEndHouseNumber = decEndHouseNumber;
    }

    public String getDecEndStreet() {
        return decEndStreet;
    }

    public void setDecEndStreet(String decEndStreet) {
        this.decEndStreet = decEndStreet;
    }

    public String getDecEndCity() {
        return decEndCity;
    }

    public void setDecEndCity(String decEndCity) {
        this.decEndCity = decEndCity;
    }

    public String getDecEndCountry() {
        return decEndCountry;
    }

    public void setDecEndCountry(String decEndCountry) {
        this.decEndCountry = decEndCountry;
    }
/*
    public int getDeclarationID() {
        return declarationID;
    }

    public void setDeclarationID(int declarationID) {
        this.declarationID = declarationID;
    }
    
 */



    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }
}