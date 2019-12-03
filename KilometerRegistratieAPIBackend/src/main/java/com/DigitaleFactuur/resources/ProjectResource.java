
package com.DigitaleFactuur.resources;

import com.DigitaleFactuur.json.ProjectJSON;
import com.DigitaleFactuur.json.UserJSON;
import com.DigitaleFactuur.models.Project;
import com.DigitaleFactuur.models.User;
import com.DigitaleFactuur.services.ClientService;
import com.DigitaleFactuur.services.ProjectService;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/project")
//https://localhost:8443/declaration
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectResource {

    private ProjectService service;

    public ProjectResource(ProjectService service) {
        this.service = service;
    }

    /**
     * Post request om een Project aan te maken
     * @param json - JSON-file met daarin de definitie v/d project.
     * @author Richard
     */

    @POST
    @Consumes("application/json")
    @UnitOfWork
    @Path("/create")
    public Project saveUser(final ProjectJSON json) {
        Project p = new Project(json.ownerID, json.projectName, json.projectDesc, json.projectStartDate, json.projectEndDate);

        return service.save(p);
    }

    /**
     * Http get request om project per projectnaam op te zoeken.
     * @param projectName
     * @return project
     * @author Richard
     */

    @GET
    @Path("/get/" + "{projectName}")
    @Produces("application/json")
    @UnitOfWork
    public Project getProjectByName(@PathParam("projectName") String projectName){
        return service.getProjectByName(projectName);
    }

    /**
     * Http get request om alle projecten per ownerID op te vragen
     * @param ownerID
     * @return ArrayList met alle projecten
     * @author Richard
     */

    @GET
    @Produces("application/json")
    @UnitOfWork
    @Path("/getProjectsByOwnerID/" + "{ownerID}")
    public ArrayList<Project> getProjectsByOwnerID(@PathParam("ownerID") int ownerID){
        return service.getProjectsByOwnerID(ownerID);
    }

    /**
     * Http delete request om projecten aan de hand van hun
     * projectnaam te verwijderen.
     * @param projectName
     * @author Tom
     */
    @DELETE
    @UnitOfWork
    @Path("/deleteProjectByName/" + "{projectName}")
    public void deleteProjectByName(@PathParam("projectName") String projectName) {
        service.deleteProjectByName(projectName);
    }
}