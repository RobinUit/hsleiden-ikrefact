
package com.DigitaleFactuur.db;

import com.DigitaleFactuur.models.Project;
import com.DigitaleFactuur.models.User;
import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;

public class ProjectDAO extends AbstractDAO<Project> {

    Connection con;

    public ProjectDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    private String SQLgetProjectByName(String name){
        return "SELECT * FROM project WHERE projectName = '" + name+ "'";
    }

    private String SQLgetProjectsByOwnerID(int ownerID){
        return "SELECT * FROM project WHERE ownerID = '" + ownerID + "'";
    }

    public String SQLdeleteProjectByName(String projectName){
        return "DELETE FROM project WHERE projectName='" + projectName + "';";
    }

    public Optional<Project> findByID(long id) {
        return Optional.fromNullable(get(id));
    }

    public Project save(Project project) {
        return persist(project);
    }

    public void delete(Project client) {
        namedQuery("com.udemy.core.Client.remove")
                .setParameter("id", client.getProjectID())
                .executeUpdate();
    }
    /**
     * Get project d.m.v. de projectnaam op te zoeken.
     * @param projectName - projectnaam
     * @return project
     * @author Richard
     */

    public Project getProjectByName(String projectName) {
        try {
            con = DatabaseConnector.getConnection();
            String getProjectByProjectNameStatement = SQLgetProjectByName(projectName);
            PreparedStatement getProjectByProjectName = con.prepareStatement(getProjectByProjectNameStatement);
            ResultSet result = getProjectByProjectName.executeQuery();
            while (result.next()) {
//                DateTime projectStartDateTime = new DateTime(result.getDate("projectStartDate"));
//                DateTime projectEndDateTime = new DateTime(result.getDate("projectEndDate"));
                Project project = new Project(
                        result.getString("projectName"),
                        result.getString("projectDesc"),
                        result.getString("projectStartDate"),
                        result.getString("projectEndDate"));
                return project;
            }
        }catch(SQLException e) {

        }

        return null;
    }

    /**
     * Get alle projecten die gelinkt zijn aan een owner via ownerID.
     * @param ownerID owner van het project
     * @return ArrayList met daarin alle projecten
     * @author Richard
     */

    public ArrayList<Project> getProjectsByOwnerID(int ownerID){
        ArrayList<Project> autos = new ArrayList<>();
        try{
            con = DatabaseConnector.getConnection();
            String getProjectsByOwnerID = SQLgetProjectsByOwnerID(ownerID);
            PreparedStatement CarsByOwnerID = con.prepareStatement(getProjectsByOwnerID);
            ResultSet rs = CarsByOwnerID.executeQuery();
            while (rs.next()){
                Project requestedProject = new Project(
                        rs.getString("projectName"),
                        rs.getString("projectDesc"),
                        rs.getString("projectStartDate"),
                        rs.getString("projectEndDate"));
                autos.add(requestedProject);
            }
        }catch(SQLException e){
        }
        return autos;
    }

    public void deleteProjectByName(String projectName) {
        try {
            con = DatabaseConnector.getConnection();
            PreparedStatement deleteCarByLicence = con.prepareStatement(SQLdeleteProjectByName(projectName));
            deleteCarByLicence.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}