package com.DigitaleFactuur.services;

import com.DigitaleFactuur.db.ProjectDAO;
import com.DigitaleFactuur.models.Project;

import java.util.ArrayList;

public class ProjectService {

    private ProjectDAO projectDAO;

    public ProjectService(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    public Project getProjectByName(String projectName) {
        return projectDAO.getProjectByName(projectName);
    }

    public ArrayList<Project> getAllProjectsByOwnerID(int ownerID){
        return projectDAO.getAllProjectsByOwnerID(ownerID);
    }

    public Project saveProject(Project newProject) {
        return projectDAO.saveProject(newProject);
    }

    public void deleteProjectByName(String projectName){
        projectDAO.deleteProjectByName(projectName);
    }
}