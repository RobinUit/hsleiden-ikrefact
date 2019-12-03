package com.DigitaleFactuur.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement
public class DeclarationJSON {

    @XmlElement public int ownerID;
    @XmlElement public String decDesc;
    @XmlElement public int decKilometers;
    @XmlElement public double decDeclaration;
    @XmlElement public String decBeginPostal;
    @XmlElement public String decBeginHouseNumber;
    @XmlElement public String decBeginStreet;
    @XmlElement public String decBeginCity;
    @XmlElement public String decBeginCountry;
    @XmlElement public String decEndPostal;
    @XmlElement public String decEndHouseNumber;
    @XmlElement public String decEndStreet;
    @XmlElement public String decEndCity;
    @XmlElement public String decEndCountry;


}

