package com.DigitaleFactuur.db;

import com.DigitaleFactuur.models.Client;
import com.google.common.base.Optional;
import com.sun.media.jfxmedia.locator.ConnectionHolder;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.sql.*;
import java.util.List;

public class ClientDAO extends AbstractDAO<Client> {

    Connection con;

    public ClientDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    private String SQLgetClientByName(String name){
        return "SELECT * FROM client WHERE clientName = '" + name+ "'";
    }

    public String SQLdeleteClientByName(String clientName){
        return "DELETE FROM client WHERE clientName='" + clientName + "';";
    }

    public Optional<Client> findByID(long id) {
        return Optional.fromNullable(get(id));
    }

    public Client save(Client client) {
        return persist(client);
    }

    public void delete(Client client) {
        namedQuery("com.udemy.core.Client.remove")
                .setParameter("id", client.getClientID())
                .executeUpdate();
    }

    /**
     * Get client d.m.v. de klantnaam op te zoeken.
     * @param clientName - klantnaam
     * @return client
     * @author Richard
     */
    public Client getClientByName(String clientName) {
        try {
            con = DatabaseConnector.getConnection();
            String getClientByClientNameStatement = SQLgetClientByName(clientName);
            PreparedStatement getClientByClientName = con.prepareStatement(getClientByClientNameStatement);
            ResultSet result = getClientByClientName.executeQuery();
            while (result.next()) {
                Client client = new Client(
                        result.getString("clientName"),
                        result.getString("clientPostalCode"),
                        result.getInt("clientHouseNumber"),
                        result.getString("clientCity"),
                        result.getString("clientCountry"));
                return client;
            }
        }catch(SQLException e) {
        }
        return null;
    }

    public void deleteClientByName(String clientName) {
        try {
            con = DatabaseConnector.getConnection();
            PreparedStatement deleteClientByName = con.prepareStatement(SQLdeleteClientByName(clientName));
            deleteClientByName.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
