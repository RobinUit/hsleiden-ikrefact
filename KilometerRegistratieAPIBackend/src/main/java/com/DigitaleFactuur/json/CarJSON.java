
package com.DigitaleFactuur.json;

import org.joda.time.LocalDate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CarJSON {
        @XmlElement public String licensePlate;
        @XmlElement public int ownerID;
        @XmlElement public String name;
        @XmlElement public String brand;
        @XmlElement public String type;
        @XmlElement public String color;
        @XmlElement public String fuelType;
}