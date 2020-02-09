package com.DigitaleFactuur.resources;

import com.DigitaleFactuur.models.User;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//https://localhost:8443/hello
@Path("/hello")
public class HelloResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @UnitOfWork
    public String getGreeting() {
        return "Hello World";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @UnitOfWork
    @Path("/secured")
    public String getSecuredGreeting(@Auth User user) {
        return "Hello secured world";
    }

    //IKREFACT DIT IS EEN TESTKLASSE
}
