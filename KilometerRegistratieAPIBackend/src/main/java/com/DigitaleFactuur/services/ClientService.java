package com.DigitaleFactuur.services;

import com.DigitaleFactuur.db.ClientDAO;
import com.DigitaleFactuur.models.Client;

public class ClientService {

    private ClientDAO clientDAO;

    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public Client getClientByName(String clientName) {
        return clientDAO.getClientByName(clientName);
    }

    public Client saveClient(Client client) {
        return clientDAO.saveClient(client);
    }

    public void deleteClientByName(String clientName) {
        clientDAO.deleteClientByName(clientName);
    }
}