package com.DigitaleFactuur.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClientJSON {
        @XmlElement public int ownerID;
        @XmlElement public String name;
        @XmlElement public String zipcode;
        @XmlElement public int housenumber;
        @XmlElement public String street;
        @XmlElement public String city;
        @XmlElement public String country;
    }

