package com.DigitaleFactuur.db;

import com.DigitaleFactuur.models.Car;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarDAO extends AbstractDAO<Car> {

    private ResultSet result = null;

    public CarDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    private static String getCarByLicensePlateQuery(String licensePlate) {
        return "SELECT * FROM car WHERE licensePlate = '" + licensePlate + "';";
    }

    private static String getCarsByOwnerIDQuery(int ownerID) {
        return "SELECT * FROM car WHERE ownerID = '" + ownerID + "';";
    }

    private static String deleteCarByLicensePlateQuery(String licensePlate) {
        return "DELETE FROM car WHERE licensePlate = '" + licensePlate + "';";
    }

    public Car getCarByLicensePlate(String licensePlate) {
        try {
            result = DatabaseConnector.executeDatabaseQuery(
                    CarDAO.getCarByLicensePlateQuery(licensePlate));
            if (result != null) {
                result.next();
                return createCar();
            }
        } catch (SQLException ignored) {
        } finally {
            DatabaseConnector.closeDatabaseConnection(result);
        }
        return null;
    }

    public ArrayList<Car> getCarsByOwnerID(int ownerID) {
        ArrayList<Car> cars = new ArrayList<>();
        try {
            result = DatabaseConnector.executeDatabaseQuery(
                    CarDAO.getCarsByOwnerIDQuery(ownerID));
            if (result != null) {
                while (result.next()) {
                    Car car = createCar();
                    cars.add(car);
                }
            }
        } catch (SQLException ignored) {
        } finally {
            DatabaseConnector.closeDatabaseConnection(result);
        }
        return cars;
    }

    public Car saveCar(Car car) {
        return persist(car);
    }

    public void deleteCarByLicensePlate(String licensePlate) {
        DatabaseConnector.executeDatabaseUpdate(
                CarDAO.deleteCarByLicensePlateQuery(licensePlate));
    }

    private Car createCar() throws SQLException {
        return new Car(
                result.getString("licensePlate"),
                result.getInt("ownerID"),
                result.getString("name"),
                result.getString("brand"),
                result.getString("type"),
                result.getString("color"),
                result.getString("fuelType"));
    }
}