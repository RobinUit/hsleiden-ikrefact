package com.DigitaleFactuur.resources;



import com.DigitaleFactuur.json.UserJSON;
import com.DigitaleFactuur.models.Declaration;
import com.DigitaleFactuur.models.User;
import com.DigitaleFactuur.services.DeclarationService;
import com.DigitaleFactuur.services.UserService;
import com.google.common.base.Optional;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private UserService service;

    public UserResource(UserService service) {
        this.service = service;
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getGreeting() {
        return "User";
    }

    @POST
    @Consumes("application/json")
    @UnitOfWork
    @Path("/create")
    public User saveUser(final UserJSON json) {
        User u = new User(json.email, json.username, json.password);
        return service.save(u);
    }

    @GET
    @Path("/get/" + "{email}")
    @Produces("application/json")
    @UnitOfWork
    public User getUserFromEmail(@PathParam("email") String email) throws SQLException {
        return service.test(email);
    }

    @GET
    @Path("/getUserByID/" + "{id}")
    @Produces("application/json")
    @UnitOfWork
    public User getUserFromEmail(@PathParam("id") int id) throws SQLException {
        return service.getUserByID(id);
    }

    @GET
    @Path("/getUserID/" + "{email}")
    @Produces("application/json")
    @UnitOfWork
    public int getUserIDByEmail(@PathParam("email") String email) {
        return service.getUserIDByEmail(email);
    }

    /**
     * Http delete request om gebruikers aan de hand van hun
     * id te verwijderen.
     * @param id
     * @throws SQLException
     */
    @DELETE
    @UnitOfWork
    @Path("/delete/" + "{id}")
    public void deleteUser(@PathParam("id") int id) throws SQLException{
        service.delete(id);
    }
}