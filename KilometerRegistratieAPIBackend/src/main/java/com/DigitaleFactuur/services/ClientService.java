
package com.DigitaleFactuur.services;

import com.DigitaleFactuur.db.ClientDAO;
import com.DigitaleFactuur.models.Client;
import com.google.common.base.Optional;

import java.sql.SQLException;
import java.util.List;

public class ClientService {

    private ClientDAO dao;

    public ClientService(ClientDAO dao) { this.dao = dao; }

//    public List<Client> getAllClients(Long id) {
//        List<Client> clientList = dao.findForUser(id);
//        return clientList;
//    }

    public Optional<Client> findByID(long id) {
        return dao.findByID(id);
    }

    public Client save(Client client) {
        return dao.save(client);
    }

    public Client getClientByName(String clientName){ return dao.getClientByName(clientName);}

    /**
     * Call the delete function from the dao.
     * @param clientName
     * @author Tom
     */
    public void deleteClientByName(String clientName){
        dao.deleteClientByName(clientName);
    }

}