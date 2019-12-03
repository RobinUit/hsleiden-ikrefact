package com.DigitaleFactuur.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClientJSON {
        @XmlElement public int ownerID;
        @XmlElement public String clientName;
        @XmlElement public String clientPostalCode;
        @XmlElement public int clientHouseNumber;
        @XmlElement public String clientCity;
        @XmlElement public String clientCountry;
    }

