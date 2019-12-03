package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Controllers.Popups.PopupController;
import sample.Models.Car;
import sample.Models.User;
import sample.Services.HTTPRequestHandler;
import sample.Utility.ActiveCar;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    private HTTPRequestHandler httpRequestHandler = new HTTPRequestHandler();
    private AppController appController = new AppController();
    private PopupController popupController = new PopupController();

    private ArrayList<Car> autos;

//    @FXML
//    private ChoiceBox<?> clientList;
//    @FXML
//    private ChoiceBox<?> projectList;
    @FXML
    private ChoiceBox<String> carList;

    @FXML
    public TextField accountNaam, accountMail, accountPassword;

    @FXML
    private Button closeButton, hideButton;

    @FXML
    public void openDeclarations(ActionEvent event) {
        Node node = (Node)event.getSource();
        appController.changeView("Declaration", node, "inherit");
    }

    @FXML
    public void openDashboard(ActionEvent event) {
        Node node = (Node)event.getSource();
        appController.changeView("Dashboard", node, "inherit");
    }

    @FXML
    public void reloadProfile(ActionEvent event) {
        Node node = (Node)event.getSource();
        appController.changeView("Profile", node, "inherit");
    }

    @FXML
    public void changePassword(ActionEvent event) {
        Node node = (Node)event.getSource();
        popupController.DisplayPopup("ChangePassword", node);
    }

    @FXML
    void add(ActionEvent event) {
        Node node = (Node)event.getSource();
        String btnid = node.getId();

        switch (btnid) {
            case "addClient":
                popupController.DisplayPopup("CreateClient", node);
                break;
            case "addProject":
                popupController.DisplayPopup("CreateProject", node);
                break;
            case "addCar":
                ActiveCar.setAuto(null);
                popupController.DisplayPopup("CreateCar", node);
                break;
        }
    }

    @FXML
    void edit(ActionEvent event) {
        Node node = (Node)event.getSource();
        String btnid = node.getId();

        switch (btnid) {
            case "editClient":
            case "editProject":
                break;
            case "editCar":
                loadCar();
                if (ActiveCar.getAuto() != null) {
                    popupController.DisplayPopup("CreateCar", node);
                }
                break;

        }

    }

    /**
     * Handelt het drukken op de verwijder knop af,
     * bij het verwijderen van klanten, projecten en auto's
     * @author Tom
     */
    @FXML
    void remove(ActionEvent event) {
        Node node = (Node)event.getSource();
        String btnid = node.getId();

        switch (btnid) {
            case "removeClient":
            case "removeProject":
                break;
            case "removeCar":
                loadCar();
                if (ActiveCar.getAuto() != null){
                    httpRequestHandler.removeFromTableById("car", ActiveCar.getAuto().getLicencePlate());
                    reloadProfile(event);
                }
                break;
        }
    }

    /**
    * opslaan account gegevens
     */
    @FXML
    void save() {
    }

    /**
     * nieuwe declaratie
     */
    @FXML
    void newDeclaration(ActionEvent event){
        Node node = (Node)event.getSource();
        appController.changeView("CreateDeclaration", node, "inherit");
    }

    /**
     * close, minimize en logout
     */
    @FXML
    public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void hide() {
        Stage stage = (Stage) hideButton.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void logout(ActionEvent event) {
        Node node = (Node)event.getSource();
        appController.changeView("Login", node, "center");
    }

    private void loadCars(ArrayList<Car> cars){
        ArrayList<String> st = new ArrayList<>();
        for (Car car : cars) {
            st.add(car.getLicencePlate());
        }
        ObservableList<String> availableChoices = FXCollections.observableArrayList(st);
        carList.setItems(availableChoices);
    }

    private void loadCar(){
        for (Car auto : autos) {
            if (auto.getLicencePlate().equals(carList.getValue())) {
                ActiveCar.setAuto(auto);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        autos = httpRequestHandler.getCarsByID("/car/getCarsByOwnerID/" + User.getUserID());
        loadCars(autos);
        User u =  httpRequestHandler.getUserByMail("/user/getUserByID/"+ User.getUserID());
        accountNaam.setText(u.getUsername());
        accountMail.setText(u.getEmailadress());
    }
}
