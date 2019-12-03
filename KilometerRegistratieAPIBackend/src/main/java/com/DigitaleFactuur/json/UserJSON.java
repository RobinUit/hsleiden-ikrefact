package com.DigitaleFactuur.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserJSON {
        @XmlElement public String email;
        @XmlElement public String username;
        @XmlElement public String password;

    }

