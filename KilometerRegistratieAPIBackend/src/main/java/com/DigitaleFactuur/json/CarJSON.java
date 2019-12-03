
package com.DigitaleFactuur.json;

import org.joda.time.LocalDate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CarJSON {
        @XmlElement public String licencePlate;
        @XmlElement public int ownerID;
        @XmlElement public String carName;
        @XmlElement public String carBrand;
        @XmlElement public String carType;
        @XmlElement public String carColor;
        @XmlElement public String fuelType;
}