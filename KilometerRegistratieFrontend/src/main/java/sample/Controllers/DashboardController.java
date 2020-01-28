package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Models.Declaration;
import sample.Models.User;
import sample.Services.HTTPRequestHandler;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private AnchorPane applicationPane;
    @FXML
    public Label totalDeclarationLabel, compensationLabel, totalKilometersLabel;
    @FXML
    public Button closeApplicationButton, minimizeApplicationButton, logoutButton, dashboardButton, profileButton, declarationButton, newDeclarationButton;

    private HTTPRequestHandler httpRequestHandler = new HTTPRequestHandler();
    private AppController appController = new AppController();

    private double totalKilometerCompensation = 0;
    private double totalKilometers = 0;
    private Declaration[] declarations = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDashboard();
    }

    @FXML
    public void loadDashboard() {

        try {
            declarations = httpRequestHandler.getDeclarationsByID("/declaration/getDeclarationsByOwnerID/" + User.getUserID());

            for (Declaration declaration : declarations) {
                totalKilometerCompensation += declaration.getDeclaredKilometers() * declaration.getDeclaredCompensation();
                totalKilometers += declaration.getDeclaredKilometers();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            setDashboardLabelValues();
        }
    }

    private void setDashboardLabelValues() {
        DecimalFormat decimalFormat = new DecimalFormat("###0.00");

        compensationLabel.setText("€ " + decimalFormat.format(totalKilometerCompensation));
        totalKilometersLabel.setText(totalKilometers + " Km");
        if (declarations != null) {
            totalDeclarationLabel.setText(Integer.toString(declarations.length));
            return;
        }
        totalDeclarationLabel.setText("0");
    }

    @FXML
    public void openDeclarations() {
        appController.changeView("Declaration", applicationPane, "inherit");
    }

    @FXML
    public void openProfile() {
        appController.changeView("Profile", applicationPane, "inherit");
    }

    @FXML
    public void openCreateDeclaration() {
        appController.changeView("CreateDeclaration", applicationPane, "inherit");
    }

    @FXML
    public void logout() {
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

