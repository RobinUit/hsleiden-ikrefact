package com.DigitaleFactuur.services;

import com.DigitaleFactuur.db.CarDAO;
import com.DigitaleFactuur.models.Car;
import java.util.ArrayList;

public class CarService {

    private CarDAO carDAO;

    public CarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public Car getCarByLicensePlate(String licensePlate) {
        return carDAO.getCarByLicensePlate(licensePlate);
    }

    public ArrayList<Car> getCarsByOwnerID(int ownerID) {
        return carDAO.getCarsByOwnerID(ownerID);
    }

    public Car saveCar(Car car) {
        return carDAO.saveCar(car);
    }

    public void deleteCarByLicensePlate(String licensePlate) {
        carDAO.deleteCarByLicensePlate(licensePlate);
    }
}