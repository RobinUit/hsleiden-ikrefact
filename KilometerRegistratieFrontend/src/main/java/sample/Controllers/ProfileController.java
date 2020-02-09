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
import javafx.scene.layout.AnchorPane;
import sample.Controllers.Popups.PopupController;
import sample.Models.Car;
import sample.Models.Client;
import sample.Models.Project;
import sample.Models.User;
import sample.Services.HTTPRequestHandler;
import sample.Utility.ActiveCar;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private AnchorPane applicationPane;
    @FXML
    private Button logoutButton, declarationButton, dashboardButton, profileButton, changePasswordButton, saveButton, removeClient, editClient, addProject, removeProject, editProject, addCar, removeCar, editCar,  addClient, plusButton, closeButton, hideButton;
    @FXML
    private ChoiceBox<String> carList, projectList, clientList;
    @FXML
    public TextField accountNaam, accountMail, accountPassword;

    private HTTPRequestHandler httpRequestHandler = new HTTPRequestHandler();
    private AppController appController = new AppController();
    private PopupController popupController = new PopupController();

    private ArrayList<Car> cars;
    private ArrayList<Client> clients;
    private ArrayList<Project> projects;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadProfile();
    }

    @FXML
    public void loadProfile() {
        cars = httpRequestHandler.getCarsByID("/car/getCarsByOwnerID/" + User.getUserID());
        loadCars(cars);
        User currentUser =  httpRequestHandler.getUserByMail("/user/getUserByID/"+ User.getUserID());
        accountNaam.setText(currentUser.getUsername());
        accountMail.setText(currentUser.getEmail());
    }

    @FXML
    void add(ActionEvent event) {
        Node node = (Node)event.getSource();
        String btnid = node.getId();

        switch (btnid) {
            case "addClient":
                popupController.openPopup("CreateClient", applicationPane);
                break;
            case "addProject":
                popupController.openPopup("CreateProject", applicationPane);
                break;
            case "addCar":
                ActiveCar.setActiveCar(null);
                popupController.openPopup("CreateCar", applicationPane);
                break;
        }
    }

    @FXML
    void edit(ActionEvent event) {
        Node node = (Node)event.getSource();
        String btnid = node.getId();

        switch (btnid) {
            case "editClient":
                //not yet implemented
                break;
            case "editProject":
                //not yet implemented
                break;
            case "editCar":
                loadCar();
                if (ActiveCar.getActiveCar() != null) {
                    popupController.openPopup("CreateCar", applicationPane);
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
                //not yet implemented
                break;
            case "removeProject":
                //not yet implemented
                break;
            case "removeCar":
                loadCar();
                if (ActiveCar.getActiveCar() != null){
                    httpRequestHandler.removeFromTableById("car", ActiveCar.getActiveCar().getLicensePlate());
                    loadProfile();
                }
                break;
        }
    }

    private void loadCars(ArrayList<Car> cars){
        ArrayList<String> st = new ArrayList<>();
        for (Car car : cars) {
            st.add(car.getLicensePlate());
        }
        ObservableList<String> availableChoices = FXCollections.observableArrayList(st);
        carList.setItems(availableChoices);
    }

    private void loadCar(){
        for (Car car : cars) {
            if (car.getLicensePlate().equals(carList.getValue())) {
                ActiveCar.setActiveCar(car);
            }
        }
    }

    @FXML
    void save() {
        //not yet implemented
    }

    @FXML
    public void changePassword() {
        popupController.openPopup("ChangePassword", applicationPane);
    }

    @FXML
    public void openDeclarations() {
        appController.changeView("Declaration", applicationPane, "inherit");
    }

    @FXML
    public void openDashboard() {
        appController.changeView("Dashboard", applicationPane, "inherit");
    }

    @FXML
    void newDeclaration(){
        appController.changeView("CreateDeclaration", applicationPane, "inherit");
    }

    @FXML
    void logout() {
        appController.logout(applicationPane);
    }

    @FXML
    public void minimizeApplication() {
        appController.minimizeApplication(applicationPane);
    }

    @FXML
    public void closeApplication() {
        appController.closeApplication(applicationPane);
    }
}
