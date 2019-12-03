package com.DigitaleFactuur.resources;

import com.DigitaleFactuur.json.CarJSON;
import com.DigitaleFactuur.models.Car;
import com.DigitaleFactuur.services.CarService;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/car")
//https://localhost:8443/declaration
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarResource {

    private CarService service;

    public CarResource(CarService service) {
        this.service = service;
    }

    //POST FUNCTIES

    /**
     * Post-request om een Auto aan te maken
     * @param json JSON-file met daarin de definitie v/d auto.
     * @return nieuw auto object
     */
    @POST
    @Consumes("application/json")
    @UnitOfWork
    @Path("/create")
    public Car createCar(final CarJSON json) {
        Car c = new Car(
                json.licencePlate,
                json.ownerID,
                json.carName,
                json.carBrand,
                json.carType,
                json.carColor,
                json.fuelType);
        return service.save(c);
    }

    //GET FUNCTIES

    /**
     * Http get request om auto per kentekenplaat op te zoeken.
     * @param licencePlate
     * @return auto
     * @author Ole
     */
    @GET
    @Produces("application/json")
    @UnitOfWork
    @Path("/getCarByLicence/"+"{licencePlate}")
    public Car getCarByLicence(@PathParam("licencePlate") String licencePlate) {
        return service.getCarByLicence(licencePlate);
    }

    /**
     * Http get request om alle auto's per ownerID op te vragen
     * @param ownerID
     * @return ArrayList met alle auto's
     * @author Ole
     */
    @GET
    @Produces("application/json")
    @UnitOfWork
    @Path("/getCarsByOwnerID/" + "{ownerID}")
    public ArrayList<Car> getCarsByOwnerID(@PathParam("ownerID") int ownerID){
        return service.getCarsByOwnerID(ownerID);
    }

    /**
     * Http delete request om auto's aan de hand van hun
     * licenceplate te verwijderen.
     * @param licencePlate
     * @author Tom
     */
    @DELETE
    @UnitOfWork
    @Path("/delete/" + "{licencePlate}")
    public void deleteCarByLicence(@PathParam("licencePlate") String licencePlate) {
        service.deleteCarByLicence(licencePlate);
    }
}