package com.DigitaleFactuur.resources;

import com.DigitaleFactuur.json.ProjectJSON;
import com.DigitaleFactuur.models.Project;
import com.DigitaleFactuur.services.ProjectService;
import io.dropwizard.hibernate.UnitOfWork;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

//https://localhost:8443/project
@Path("/project")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectResource {

    private ProjectService projectService;

    public ProjectResource(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GET
    @Produces("application/json")
    @UnitOfWork
    @Path("/get/" + "{name}")
    public Project getProjectByName(@PathParam("name") String name){
        return projectService.getProjectByName(name);
    }

    @GET
    @Produces("application/json")
    @UnitOfWork
    @Path("/getProjectsByOwnerID/" + "{ownerID}")
    public ArrayList<Project> getProjectsByOwnerID(@PathParam("ownerID") int ownerID){
        return projectService.getAllProjectsByOwnerID(ownerID);
    }

    @POST
    @Consumes("application/json")
    @UnitOfWork
    @Path("/create")
    public Project saveProject(final ProjectJSON json) {
        Project project = new Project(
                json.ownerID,
                json.name,
                json.description,
                json.startDate,
                json.endDate);

        return projectService.saveProject(project);
    }

    @DELETE
    @UnitOfWork
    @Path("/deleteProjectByName/" + "{name}")
    public void deleteProjectByName(@PathParam("name") String name) {
        projectService.deleteProjectByName(name);
    }
}