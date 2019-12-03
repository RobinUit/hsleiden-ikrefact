package com.DigitaleFactuur.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.security.Principal;

@Entity
@Table(name = "project")
public class Project implements Principal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @MapKeyColumn
    private int projectID;

    private int ownerID;
    private String projectName;
    private String projectDesc;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String projectStartDate;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String projectEndDate;

    public Project(String projectName, String projectDesc, String projectStartDate, String projectEndDate) {
        this.projectName = projectName;
        this.projectDesc = projectDesc;
        this.projectStartDate = projectStartDate;
        this.projectEndDate = projectEndDate;
    }

    public Project(int ownerID, String projectName, String projectDesc, String projectStartDate, String projectEndDate) {
        this.ownerID = ownerID;
        this.projectName = projectName;
        this.projectDesc = projectDesc;
        this.projectStartDate = projectStartDate;
        this.projectEndDate = projectEndDate;
    }

    public int getProjectID() {
        return projectID;
    }
    public int getOwnerID(){return ownerID;}
    public void setOwnerID(int ownerID){
        this.ownerID = ownerID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public String getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(String projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public String getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(String projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    @Override
    public String getName() {
        return projectName;
    }
}
