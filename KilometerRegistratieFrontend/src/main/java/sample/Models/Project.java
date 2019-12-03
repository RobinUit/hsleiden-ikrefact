package sample.Models;

public class Project {
    private int ownerID;
    private String projectName;
    private String projectDesc;
    private String projectStartDate;
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

    public int getOwnerID(){return ownerID;}
    public void setOwnerID(int ownerID){
        this.ownerID = ownerID;
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
}


