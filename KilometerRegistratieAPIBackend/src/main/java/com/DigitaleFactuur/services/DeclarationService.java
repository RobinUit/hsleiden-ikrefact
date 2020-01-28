package com.DigitaleFactuur.services;

import com.DigitaleFactuur.db.DeclarationDAO;
import com.DigitaleFactuur.models.Declaration;
import java.util.ArrayList;

public class DeclarationService {

    private DeclarationDAO declarationDAO;

    public DeclarationService(DeclarationDAO declarationDAO) {
        this.declarationDAO = declarationDAO;
    }

    public ArrayList<Declaration> getDeclarationsByOwnerID(int ownerID) {
        return declarationDAO.getDeclarationsByOwnerID(ownerID);
    }

    public Declaration saveDeclaration(Declaration declaration) {
        return declarationDAO.saveDeclaration(declaration);
    }

    public void deleteDeclarationByID(int declarationID) {
        declarationDAO.deleteDeclarationByID(declarationID);
    }
}