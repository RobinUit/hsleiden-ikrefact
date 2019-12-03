package com.DigitaleFactuur.services;

import com.DigitaleFactuur.db.ClientDAO;
import com.DigitaleFactuur.db.DeclarationDAO;
import com.DigitaleFactuur.models.Client;
import com.DigitaleFactuur.models.Declaration;
import com.google.common.base.Optional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeclarationService {

    private DeclarationDAO dao;

    public DeclarationService(DeclarationDAO dao) { this.dao = dao; }

    public List<Declaration> getAllDeclaration(Long id) {
        List<Declaration> declarationList = dao.findForUser(id);
        return declarationList;
    }

    public Optional<Declaration> findByID(long id) {
        return dao.findByID(id);
    }

    public Declaration save(Declaration declaration) {
        return dao.save(declaration);
    }

    public void deleteDeclarationByID(int declarationID){
        dao.deleteDeclarationByID(declarationID);
    }
    public ArrayList<Declaration> getDeclarationsByOwnerID(int ownerID){
        return dao.getDeclarationsByOwnerID(ownerID);
    }

    public void deleteById(int decID) {
        try {
            dao.deleteDeclarationById(decID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}