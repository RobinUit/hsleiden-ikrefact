package com.DigitaleFactuur.resources;

import com.DigitaleFactuur.json.ClientJSON;
import com.DigitaleFactuur.models.Client;
import com.DigitaleFactuur.services.ClientService;
import io.dropwizard.hibernate.UnitOfWork;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//https://localhost:8443/client
@Path("/client")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {

    private ClientService clientService;

    public ClientResource(ClientService clientService) {
        this.clientService = clientService;
    }

    @GET
    @Produces("application/json")
    @UnitOfWork
    @Path("/get/" + "{clientName}")
    public Client getClientByName(@PathParam("clientName") String name){
        return clientService.getClientByName(name);
    }

    @POST
    @Consumes("application/json")
    @UnitOfWork
    @Path("/create")
    public Client saveClient(final ClientJSON json) {
        Client client = new Client(
                json.ownerID,
                json.name,
                json.zipcode,
                json.housenumber,
                json.street,
                json.city,
                json.country
        );
        return clientService.saveClient(client);
    }

    @DELETE
    @UnitOfWork
    @Path("/deleteClientByName/" + "{clientName}")
    public void deleteClientByName(@PathParam("clientName") String name) {
        clientService.deleteClientByName(name);
    }

}