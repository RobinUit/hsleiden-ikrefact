package com.DigitaleFactuur.resources;

import com.DigitaleFactuur.json.UserJSON;
import com.DigitaleFactuur.models.User;
import com.DigitaleFactuur.services.UserService;
import io.dropwizard.hibernate.UnitOfWork;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Produces("application/json")
    @UnitOfWork
    @Path("/get/" + "{email}")
    public User getUserByEmail(@PathParam("email") String email) {
        return userService.getUserByEmail(email);
    }

    @GET
    @Produces("application/json")
    @UnitOfWork
    @Path("/getUserByID/" + "{id}")
    public User getUserByID(@PathParam("id") int id) {
        return userService.getUserByID(id);
    }

    @GET
    @Produces("application/json")
    @UnitOfWork
    @Path("/getUserID/" + "{email}")
    public int getUserIDByEmail(@PathParam("email") String email) {
        return userService.getUserIDByEmail(email);
    }

    @POST
    @Consumes("application/json")
    @UnitOfWork
    @Path("/create")
    public User saveUser(final UserJSON json) {
        User user = new User(
                json.email,
                json.username,
                json.password);
        return userService.saveUser(user);
    }

    @DELETE
    @UnitOfWork
    @Path("/delete/" + "{id}")
    public void deleteUser(@PathParam("id") int id) {
        userService.deleteUserByID(id);
    }
}