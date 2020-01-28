package com.DigitaleFactuur.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DeclarationJSON {

    @XmlElement public int ownerID;

    @XmlElement public String description;
    @XmlElement public double declaredKilometers;
    @XmlElement public double declaredCompensation;

    @XmlElement public String originZipcode;
    @XmlElement public String originHouseNumber;
    @XmlElement public String originStreet;
    @XmlElement public String originCity;
    @XmlElement public String originCountry;

    @XmlElement public String destinationZipcode;
    @XmlElement public String destinationHouseNumber;
    @XmlElement public String destinationStreet;
    @XmlElement public String destinationCity;
    @XmlElement public String destinationCountry;


}

