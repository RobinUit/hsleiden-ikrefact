package com.DigitaleFactuur.db;

import com.DigitaleFactuur.models.Declaration;
import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeclarationDAO extends AbstractDAO<Declaration> {

    Connection con;

    public DeclarationDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Declaration> findForUser(long id) {
        return list(
                namedQuery("com.udemy.core.Declaration.findForUser")
                        .setParameter("id", id)
        );
    }

    public String deleteQuery(int id){
        return "DELETE FROM declaration WHERE declarationID='" + id + "';";
    }

    private String SQLgetDeclarationByOwnerID(int ownerID){
        return "SELECT * FROM declaration WHERE ownerID = '" + ownerID + "'";
    }

    public String SQLdeleteDeclarationByOwnerID(int declarationID){
        return "DELETE FROM declaration WHERE declarationID='" + declarationID + "';";
    }


    public ArrayList<Declaration> getDeclarationsByOwnerID(int ownerID){
        ArrayList<Declaration> decs = new ArrayList<>();
        try{
            con = DatabaseConnector.getConnection();
            String getCarsByOwnerID = SQLgetDeclarationByOwnerID(ownerID);
            PreparedStatement CarsByOwnerID = con.prepareStatement(getCarsByOwnerID);
            ResultSet result = CarsByOwnerID.executeQuery();
            while (result.next()){
                Declaration dec = new Declaration(
                        result.getInt("declarationID"),
                        result.getInt("ownerID"),
                        result.getString("decDesc"),
                        result.getInt("decKilometers"),
                        result.getDouble("decDeclaration"),
                        result.getString("decBeginPostal"),
                        result.getString("decBeginHouseNumber"),
                        result.getString("decBeginStreet"),
                        result.getString("decBeginCity"),
                        result.getString("decBeginCountry"),
                        result.getString("decEndPostal"),
                        result.getString("decEndHouseNumber"),
                        result.getString("decEndStreet"),
                        result.getString("decEndCity"),
                        result.getString("decEndCountry"));
                decs.add(dec);
            }
        }catch(SQLException e){
        }
        return decs;
    }

    public void deleteDeclarationById(int id) throws SQLException {
        try {
            con = DatabaseConnector.getConnection();
            PreparedStatement deleteDeclarationById = con.prepareStatement(deleteQuery(id));
            deleteDeclarationById.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Declaration> findByID(long id) {
        return Optional.fromNullable(get(id));
    }

    public Declaration save(Declaration declaration) {
        return persist(declaration);
    }

    public void delete(Declaration declaration) {
        namedQuery("com.udemy.core.Declaration.remove")
                .setParameter("id", declaration.getDeclarationID())
                .executeUpdate();
    }

    public void deleteDeclarationByID(int declarationID) {
        try {
            con = DatabaseConnector.getConnection();
            PreparedStatement deleteCarByLicence = con.prepareStatement(SQLdeleteDeclarationByOwnerID(declarationID));
            deleteCarByLicence.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}