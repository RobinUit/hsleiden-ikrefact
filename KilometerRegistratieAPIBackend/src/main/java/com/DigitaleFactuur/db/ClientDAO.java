package com.DigitaleFactuur.db;

import com.DigitaleFactuur.models.Client;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAO extends AbstractDAO<Client> {

    private ResultSet result = null;

    public ClientDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    private static String getClientByNameQuery(String name) {
        return "SELECT * FROM client WHERE name = '" + name + "';";
    }

    private static String deleteClientByNameQuery(String name) {
        return "DELETE FROM client WHERE name = '" + name + "';";
    }

    public Client getClientByName(String clientName) {
        try {
            result = DatabaseConnector.executeDatabaseQuery(
                    ClientDAO.getClientByNameQuery(clientName));
            if (result != null) {
                result.next();
                return createClient();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeDatabaseConnection(result);
        }
        return null;
    }

    public Client saveClient(Client client) {
        return persist(client);
    }

    public void deleteClientByName(String clientName) {
        DatabaseConnector.executeDatabaseUpdate(
                ClientDAO.deleteClientByNameQuery(clientName));
    }

    private Client createClient() throws SQLException {
        return new Client(
                result.getInt("ownerID"),
                result.getString("name"),
                result.getString("zipcode"),
                result.getInt("housenumber"),
                result.getString("street"),
                result.getString("city"),
                result.getString("country"));
    }


}
