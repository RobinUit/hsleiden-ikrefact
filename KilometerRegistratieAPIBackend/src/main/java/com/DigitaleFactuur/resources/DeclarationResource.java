package com.DigitaleFactuur.resources;

import com.DigitaleFactuur.json.DeclarationJSON;
import com.DigitaleFactuur.models.Declaration;
import com.DigitaleFactuur.models.User;
import com.DigitaleFactuur.services.DeclarationService;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

//https://localhost:8443/declaration
@Path("/declaration")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeclarationResource {

    private DeclarationService declarationService;

    public DeclarationResource(DeclarationService declarationService) {
        this.declarationService = declarationService;
    }

    @GET
    @Produces("application/json")
    @UnitOfWork
    @Path("/getDeclarationsByOwnerID/" + "{ownerID}")
    public ArrayList<Declaration> getDeclarationsByOwnerID(@PathParam("ownerID") int ownerID){
        return declarationService.getDeclarationsByOwnerID(ownerID);
    }

    @POST
    @Consumes("application/json")
    @UnitOfWork
    @Path("/create")
    public Declaration saveDeclaration(final DeclarationJSON json) {
        Declaration declaration = new Declaration(
                json.ownerID,
                json.description,
                json.declaredKilometers,
                json.declaredCompensation,
                json.originZipcode,
                json.originHouseNumber,
                json.originStreet,
                json.originCity,
                json.originCountry,
                json.destinationZipcode,
                json.destinationHouseNumber,
                json.destinationStreet,
                json.destinationCity,
                json.destinationCountry
        );
        return declarationService.saveDeclaration(declaration);
    }

    @DELETE
    @UnitOfWork
    @Path("/delete/{id}")
    public void deleteDeclarationByID(@PathParam("id")int id){
        declarationService.deleteDeclarationByID(id);
    }
}