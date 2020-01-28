package com.DigitaleFactuur.resources;

import com.DigitaleFactuur.json.CarJSON;
import com.DigitaleFactuur.models.Car;
import com.DigitaleFactuur.services.CarService;
import io.dropwizard.hibernate.UnitOfWork;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

//https://localhost:8443/car
@Path("/car")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarResource {

    private CarService carService;

    public CarResource(CarService carService) {
        this.carService = carService;
    }

    @GET
    @Produces("application/json")
    @UnitOfWork
    @Path("/getCarByLicense/"+"{licensePlate}")
    public Car getCarByLicensePlate(@PathParam("licensePlate") String licensePlate) {
        return carService.getCarByLicensePlate(licensePlate);
    }

    @GET
    @Produces("application/json")
    @UnitOfWork
    @Path("/getCarsByOwnerID/" + "{ownerID}")
    public ArrayList<Car> getCarsByOwnerID(@PathParam("ownerID") int ownerID){
        return carService.getCarsByOwnerID(ownerID);
    }

    @POST
    @Consumes("application/json")
    @UnitOfWork
    @Path("/create")
    public Car saveCar(final CarJSON json) {
        Car car = new Car(
                json.licensePlate,
                json.ownerID,
                json.name,
                json.brand,
                json.type,
                json.color,
                json.fuelType);
        return carService.saveCar(car);
    }

    @DELETE
    @UnitOfWork
    @Path("/delete/" + "{licensePlate}")
    public void deleteCarByLicensePlate(@PathParam("licensePlate") String licensePlate) {
        carService.deleteCarByLicensePlate(licensePlate);
    }
}