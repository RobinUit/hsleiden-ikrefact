package com.DigitaleFactuur.db;

import com.google.common.base.Optional;
import com.DigitaleFactuur.models.Car;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import java.sql.*;
import java.util.ArrayList;

public class CarDAO extends AbstractDAO<Car> {

    Connection con;

    public CarDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    //GETCAR BY LICENCE
    private String SQLgetCarByLicence(String licence){
        return "SELECT * FROM car WHERE licencePlate = '" + licence+ "'";
    }
    private String SQLgetCarsByOwnerID(int ownerID){
        return "SELECT * FROM car WHERE ownerID = '" + ownerID + "'";
    }

    public String SQLdeleteCarByLicence(String licence){
        return "DELETE FROM car WHERE licencePlate='" + licence + "';";
    }


    public Optional<Car> findByID(long id) {
        return Optional.fromNullable(get(id));
    }

    public Car save(Car car) {
        return persist(car);
    }

    /**
     * Delete car uit de database aan de hand van een
     * prepared SQl Statement waarin de auto zijn
     * licenceplate meegegeven wordt.
     * @param licence
     * @author Tom
     */
    public void deleteCarByLicence(String licence) {
        try {
            con = DatabaseConnector.getConnection();
            PreparedStatement deleteCarByLicence = con.prepareStatement(SQLdeleteCarByLicence(licence));
            deleteCarByLicence.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get car d.m.v. het kentekenplaat op te zoeken.
     * @param licencePlate - kentekenplaat
     * @return auto
     * @author Ole
     */
    public Car getCarByLicence(String licencePlate){
        Car requestedCar = new Car("a", "a", "a", "a", "a", "a");
        try{
            con = DatabaseConnector.getConnection();
            String getCarByLicencecePlate = SQLgetCarByLicence(licencePlate);
            PreparedStatement getCarByLicencePlate = con.prepareStatement(getCarByLicencecePlate);
            ResultSet rs = getCarByLicencePlate.executeQuery();
            if (rs.next()){
                requestedCar = new Car(
                        rs.getString("licencePlate"),
                        rs.getString("carName"),
                        rs.getString("carBrand"),
                        rs.getString("carType"),
                        rs.getString("carColor"),
                        rs.getString("fuelType"));
            }
        }catch(SQLException e){
        }
        return requestedCar;
    }

    /**
     * Get alle auto's die gelinkt zijn aan een owner via ownerID.
     * @param ownerID owner van de auto
     * @return ArrayList met daarin alle auto's
     * @author Ole
     */
    public ArrayList<Car> getCarsByOwnerID(int ownerID){
        ArrayList<Car> autos = new ArrayList<>();
        try{
            con = DatabaseConnector.getConnection();
            String getCarsByOwnerID = SQLgetCarsByOwnerID(ownerID);
            PreparedStatement CarsByOwnerID = con.prepareStatement(getCarsByOwnerID);
            ResultSet rs = CarsByOwnerID.executeQuery();
            while (rs.next()){
                Car reqeuestedCar = new Car(
                        rs.getString("licencePlate"),
                        rs.getString("carName"),
                        rs.getString("carBrand"),
                        rs.getString("carType"),
                        rs.getString("carColor"),
                        rs.getString("fuelType"));
                autos.add(reqeuestedCar);
            }
        }catch(SQLException e){
        }
        return autos;
    }
}