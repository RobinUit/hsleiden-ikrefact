package com.DigitaleFactuur.db;

import com.DigitaleFactuur.models.Project;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ProjectDAO extends AbstractDAO<Project> {

    private ResultSet result = null;

    public ProjectDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    private static String getProjectByNameQuery(String name) {
        return "SELECT * FROM project WHERE name = '" + name + "';";
    }

    private static String getProjectsByOwnerIDQuery(int ownerID) {
        return "SELECT * FROM project WHERE ownerID = '" + ownerID + "';";
    }

    private static String deleteProjectByNameQuery(String name) {
        return "DELETE FROM project WHERE name = '" + name + "';";
    }

    public Project getProjectByName(String projectName) {
        try {
            result = DatabaseConnector.executeDatabaseQuery(ProjectDAO.getProjectByNameQuery(projectName));
            if (result != null) {
                result.next();
                return createProject();
            }
        } catch (SQLException ignored) {
        } finally {
            DatabaseConnector.closeDatabaseConnection(result);
        }
        return null;
    }

    public ArrayList<Project> getAllProjectsByOwnerID(int ownerID) {
        ArrayList<Project> projects = new ArrayList<>();
        try {
            result = DatabaseConnector.executeDatabaseQuery(ProjectDAO.getProjectsByOwnerIDQuery(ownerID));
            if (result != null) {
                while (result.next()) {
                    Project project = createProject();
                    projects.add(project);
                }
            }
        } catch (SQLException ignored) {
        } finally {
            DatabaseConnector.closeDatabaseConnection(result);
        }
        return projects;
    }

    public Project saveProject(Project project) {
        return persist(project);
    }


    public void deleteProjectByName(String name) {
        DatabaseConnector.executeDatabaseUpdate(ProjectDAO.deleteProjectByNameQuery(name));
    }

    private Project createProject() throws SQLException {
        return new Project(
                result.getInt("ownerID"),
                result.getString("name"),
                result.getString("description"),
                result.getString("startDate"),
                result.getString("endDate"));
    }
}