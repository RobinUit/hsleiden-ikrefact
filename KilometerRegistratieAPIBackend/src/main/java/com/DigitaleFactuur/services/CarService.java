package com.DigitaleFactuur.services;

import com.DigitaleFactuur.db.CarDAO;
import com.DigitaleFactuur.models.Car;

import java.util.ArrayList;

public class CarService {

    private CarDAO dao;

    public CarService(CarDAO dao) {
        this.dao = dao;
    }

    public Car save(Car car) {
        return dao.save(car);
    }

    public Car getCarByLicence(String licencePlate){
        return dao.getCarByLicence(licencePlate);
    }

    public ArrayList<Car> getCarsByOwnerID(int ownerID){
        return dao.getCarsByOwnerID(ownerID);
    }

    /**
     * Call the delete function from the dao.
     * @param licencePlate
     * @author Tom
     */
    public void deleteCarByLicence(String licencePlate) {dao.deleteCarByLicence(licencePlate); }
}