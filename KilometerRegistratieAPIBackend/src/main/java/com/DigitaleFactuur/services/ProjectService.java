package com.DigitaleFactuur.services;

import com.DigitaleFactuur.db.ProjectDAO;
import com.DigitaleFactuur.db.UserDAO;
import com.DigitaleFactuur.models.Project;
import com.DigitaleFactuur.models.User;

import java.util.ArrayList;

public class ProjectService {

    private ProjectDAO dao;

    public ProjectService(ProjectDAO dao) {
        this.dao = dao;
    }

    public Project save(Project project) {
        return dao.save(project);
    }

    public Project getProjectByName(String projectName) {return dao.getProjectByName(projectName);}

    public ArrayList<Project> getProjectsByOwnerID(int ownerID){
        return dao.getProjectsByOwnerID(ownerID);
    }

    /**
     * Call the delete function from the dao.
     * @param projectName
     * @author Tom
     */
    public void deleteProjectByName(String projectName){
        dao.deleteProjectByName(projectName);
    }
}