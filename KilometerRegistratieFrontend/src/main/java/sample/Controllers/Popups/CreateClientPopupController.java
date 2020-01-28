package sample.Controllers.Popups;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Models.Client;
import sample.Models.User;
import sample.Services.HTTPRequestHandler;

public class CreateClientPopupController {

    @FXML
    private AnchorPane popupPane;
    @FXML
    private Button saveButton, closeButton;
    @FXML
    private TextField nameField, zipcodeField, housenumberField, streetNameField, cityField, countryField;

    private HTTPRequestHandler httpRequestHandler = new HTTPRequestHandler();
    private PopupController popupController = new PopupController();

    @FXML
    void closePopup() {
        popupController.closePopup(popupPane);
    }

    @FXML
    void save(ActionEvent event){
        int ownerID = User.getUserID();
        String name = nameField.getText();
        String zipcode =  zipcodeField.getText();
        String street = streetNameField.getText();
        int housenumber = Integer.parseInt(housenumberField.getText());
        String city = cityField.getText();
        String country = countryField.getText();
        try{
            Client client = new Client(ownerID, name, zipcode, housenumber, street, city, country);
            httpRequestHandler.postHandler("/client/create", client);
            Node node = (Node)event.getSource();
            Stage stage = (Stage)node.getScene().getWindow();
            stage.close();
        }catch(Exception ignored){
        }
    }

}