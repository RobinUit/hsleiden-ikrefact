package sample.Controllers.Popups;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Models.Client;
import sample.Models.User;
import sample.Services.HTTPRequestHandler;

public class CreateClientPopupController {

    private HTTPRequestHandler httpRequestHandler = new HTTPRequestHandler();

    @FXML
    private TextField nameField, postalField, houseNumberField, cityField, countryField;


    @FXML
    void close(ActionEvent event) {
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save(ActionEvent event){
        int ownerID = User.getUserID();
        String name = nameField.getText();
        String adres =  postalField.getText();

        String city = cityField.getText();
        String country = countryField.getText();
        try{
            int hnumber = Integer.parseInt(houseNumberField.getText());
            Client client = new Client(ownerID, name, adres, hnumber, city, country);
            httpRequestHandler.postHandler("/client/create", client);
            Node node = (Node)event.getSource();
            Stage stage = (Stage)node.getScene().getWindow();
            stage.close();
        }catch(Exception ignored){
        }
    }

}