package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Models.Declaration;
import sample.Models.User;
import sample.Services.HTTPRequestHandler;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    private AppController appController = new AppController();
    private HTTPRequestHandler httpRequestHandler = new HTTPRequestHandler();

    @FXML
    public Label totalDeclarationLabel, compensationLabel, totalKilometers;

    @FXML
    public Button closeButton, hideButton, logoutButton;

    @FXML
    public void openDeclarations(ActionEvent event) {
        Node node = (Node)event.getSource();
        appController.changeView("Declaration", node, "inherit");
    }

    @FXML
    public void reloadDashboard(ActionEvent event) {
        Node node = (Node)event.getSource();
        appController.changeView("Dashboard", node, "inherit");
    }

    @FXML
    public void openProfile(ActionEvent event) {
        Node node = (Node)event.getSource();
        appController.changeView("Profile", node, "inherit");
    }

    @FXML
    public void openCreateDeclaration(ActionEvent event) {
        Node node = (Node)event.getSource();
        appController.changeView("CreateDeclaration", node, "inherit");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DecimalFormat df = new DecimalFormat("###0.00");

        try {
            Declaration[] d = httpRequestHandler.getDeclarationsByID("/declaration/getDeclarationsByOwnerID/" + User.getUserID());
            double money = 0;
            double km = 0;
            for (Declaration declaration : d) {
                money += declaration.getDecKilometers() * declaration.getDecDeclaration();
                km += declaration.getDecKilometers();
            }
            totalDeclarationLabel.setText(Integer.toString(d.length));
            compensationLabel.setText("€ " + df.format(money));
            totalKilometers.setText(km + " Km");
        }catch(Exception e ){
            totalDeclarationLabel.setText("0");
            compensationLabel.setText("€0.00");
            totalKilometers.setText("0");
        }
    }

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
    public void logout(ActionEvent event) {
        Node node = (Node)event.getSource();
        appController.changeView("Login", node, "center");
    }
}

