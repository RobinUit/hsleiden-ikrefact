package com.DigitaleFactuur.models;

import javax.persistence.*;
import java.security.Principal;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @MapKeyColumn
    private int projectID;

    private int ownerID;
    private String name;
    private String description;
    private String startDate;
    private String endDate;

    public Project(int ownerID, String name, String description, String startDate, String endDate) {
        this.ownerID = ownerID;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getProjectID() {
        return projectID;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
