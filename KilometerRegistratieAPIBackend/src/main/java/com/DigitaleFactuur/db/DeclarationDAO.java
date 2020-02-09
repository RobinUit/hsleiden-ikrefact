package com.DigitaleFactuur.db;

import com.DigitaleFactuur.models.Declaration;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class DeclarationDAO extends AbstractDAO<Declaration> {

    private ResultSet result = null;

    public DeclarationDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    private static String getDeclarationByOwnerIDQuery(int ownerID) {
        return "SELECT * FROM declaration WHERE ownerID = '" + ownerID + "'";
    }

    private static String deleteDeclarationByIDQuery(int declarationID) {
        return "DELETE FROM declaration WHERE declarationID = '" + declarationID + "';";
    }

    private static String deleteDeclarationByOwnerIDQuery(int ownerID) {
        return "DELETE FROM declaration WHERE ownerID = '" + ownerID + "';";
    }

    public ArrayList<Declaration> getDeclarationsByOwnerID(int ownerID) {
        ArrayList<Declaration> declarations = new ArrayList<>();
        try {
            result = DatabaseConnector.executeDatabaseQuery(getDeclarationByOwnerIDQuery(ownerID));
            if (result != null) {
                while (result.next()) {
                    Declaration declaration = createDeclaration();
                    declarations.add(declaration);
                }
            }
        } catch (SQLException ignored) {
        } finally {
            DatabaseConnector.closeDatabaseConnection(result);
        }
        return declarations;
    }

    public Declaration saveDeclaration(Declaration declaration) {
        return persist(declaration);
    }

    public void deleteDeclarationByID(int declarationID) {
        DatabaseConnector.executeDatabaseUpdate(deleteDeclarationByIDQuery(declarationID));
    }

    //wordt niet gebruikt
    public void deleteDeclarationByOwnerID(int ownerID) {
        DatabaseConnector.executeDatabaseUpdate(deleteDeclarationByOwnerIDQuery(ownerID));
    }

    private Declaration createDeclaration() throws SQLException {
        return new Declaration(
                result.getInt("declarationID"),
                result.getInt("ownerID"),
                result.getString("description"),
                result.getInt("declaredKilometers"),
                result.getDouble("declaredCompensation"),
                result.getString("originZipcode"),
                result.getString("originHousenumber"),
                result.getString("originStreet"),
                result.getString("originCity"),
                result.getString("originCountry"),
                result.getString("destinationZipcode"),
                result.getString("destinationHousenumber"),
                result.getString("destinationStreet"),
                result.getString("destinationCity"),
                result.getString("destinationCountry"));
    }
}