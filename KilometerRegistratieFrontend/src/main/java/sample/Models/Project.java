package sample.Models;

public class Project {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}


