package com.DigitaleFactuur.json;

import org.joda.time.LocalDate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class ProjectJSON {
        @XmlElement public int ownerID;
        @XmlElement public String name;
        @XmlElement public String description;
        @XmlElement public String startDate;
        @XmlElement public String endDate;
    }

