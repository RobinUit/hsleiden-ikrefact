package com.DigitaleFactuur.resources;

import com.DigitaleFactuur.db.ClientDAO;
import com.DigitaleFactuur.json.CarJSON;
import com.DigitaleFactuur.json.ClientJSON;
import com.DigitaleFactuur.models.Car;
import com.DigitaleFactuur.models.Client;
import com.DigitaleFactuur.models.User;
import com.DigitaleFactuur.services.ClientService;
import com.google.common.base.Optional;
import com.mysql.cj.MysqlConnection;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/client")
//https://localhost:8443/client
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {

    private ClientService service;

    public ClientResource(ClientService service) {
        this.service = service;
    }

    /**
     * Http get request om auto per klantnaam op te zoeken.
     * @param clientName
     * @return client
     * @author Richard
     */
    @GET
    @Path("/get/" + "{clientName}")
    @Produces("application/json")
    @UnitOfWork
    public Client getClientByName(@PathParam("clientName") String clientName){
        return service.getClientByName(clientName);
    }

    /**
     * Post request om een Klant aan te maken
     * @param json - JSON-file met daarin de definitie v/d klant.
     * @author Richard
     */
    @POST
    @Consumes("application/json")
    @UnitOfWork
    @Path("/create")
    public Client saveClient(final ClientJSON json) {
        Client c = new Client(
                json.ownerID,
                json.clientName,
                json.clientPostalCode,
                json.clientHouseNumber,
                json.clientCity,
                json.clientCountry
        );
        return service.save(c);
    }

    /**
     * Http delete request om klanten aan de hand van hun
     * id te verwijderen.
     * @param clientName
     * @author Tom
     */
    @DELETE
    @UnitOfWork
    @Path("/deleteClientByName/" + "{clientName}")
    public void deleteClientByName(@PathParam("clientName") String clientName) {
        service.deleteClientByName(clientName);
    }

}