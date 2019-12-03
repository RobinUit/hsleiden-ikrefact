package com.DigitaleFactuur.resources;



import com.DigitaleFactuur.db.DeclarationDAO;
import com.DigitaleFactuur.json.CarJSON;
import com.DigitaleFactuur.json.DeclarationJSON;
import com.DigitaleFactuur.models.Car;
import com.DigitaleFactuur.models.Declaration;
import com.DigitaleFactuur.models.User;
import com.DigitaleFactuur.services.DeclarationService;
import com.google.common.base.Optional;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/declaration")
//https://localhost:8443/declaration
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeclarationResource {

    private DeclarationService service;

    public DeclarationResource(DeclarationService service) {
        this.service = service;
    }

    @GET
    @UnitOfWork
    public List<Declaration> getDeclaration(@Auth User user) {
        return service.getAllDeclaration(user.getId());
    }


    @POST
    @Consumes("application/json")
    @UnitOfWork
    @Path("/create")
    public Declaration saveUser(final DeclarationJSON json) {
        Declaration d = new Declaration(
                json.ownerID,
                json.decDesc,
                json.decKilometers,
                json.decDeclaration,
                json.decBeginPostal,
                json.decBeginHouseNumber,
                json.decBeginStreet,
                json.decBeginCity,
                json.decBeginCountry,
                json.decEndPostal,
                json.decEndHouseNumber,
                json.decEndStreet,
                json.decEndCity,
                json.decEndCountry
        );
        return service.save(d);
    }

    @GET
    @Produces("application/json")
    @UnitOfWork
    @Path("/getDeclarationsByOwnerID/" + "{ownerID}")
    public ArrayList<Declaration> getDeclarationsByOwnerID(@PathParam("ownerID") int ownerID){
        return service.getDeclarationsByOwnerID(ownerID);
    }

    @DELETE
    @Path("/delete/{decid}")
    public void deleteById(@PathParam("decid")int decID){
        service.deleteById(decID);
    }
}