package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import sample.Controllers.Popups.PopupController;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import sample.Models.Declaration;
import sample.Models.User;
import sample.Services.GoogleMapsHandler;
import sample.Services.HTTPRequestHandler;

public class CreateDeclarationController implements Initializable {

    private AppController appController = new AppController();
    private PopupController popupController = new PopupController();
    private GoogleMapsHandler googleMapsHandler = new GoogleMapsHandler();
    private HTTPRequestHandler httpRequestHandler = new HTTPRequestHandler();

    @FXML
    public TextField beginZipCode, beginHouseNumber, beginStreet, beginCity, beginCountry, endZipCode, endHouseNumber, endStreet, endCity, endCountry, kilometersKilometers, kilometersCompensation;
    @FXML
    public DatePicker dateDate;
    @FXML
    public Text incorrectKilometers, incorrectCompensation, incorrectDate, kilometersTraveled, compensationPerKilometer, totalCompensation;
    @FXML
    public BorderPane googleMaps;
    @FXML
    public ImageView mapImage;
    @FXML
    public Button closeButton;
    @FXML
    public TextArea kilometerDescription;
    @FXML
    public Label emptyValues;

    private void checkAddress() {
        String beginH = beginHouseNumber.getText();
        String beginS = beginStreet.getText();
        String beginZ = beginZipCode.getText();
        String endH = endHouseNumber.getText();
        String endS = endStreet.getText();
        String endZ = endZipCode.getText();
        if (beginH.trim().isEmpty() || beginS.trim().isEmpty() || beginZ.trim().isEmpty() || endH.trim().isEmpty() || endS.trim().isEmpty() || endZ.trim().isEmpty()) {
            emptyValues.setVisible(true);
            return;
        }

        try {
            String origin = beginH.trim() + "+" + beginS.trim().replaceAll(" ", "+") + "," + beginZ.trim();
            String destination = endH.trim() + "+" + endS.trim().replaceAll(" ", "+") + "," + endZ.trim();

            String[][] records = googleMapsHandler.calculate(origin, destination);

            googleMaps.setCenter(mapImage);
            mapImage.setImage(googleMapsHandler.maps(origin, destination));

            beginCity.setText(records[0][3]);

            if (records[0][4].equals("Netherlands")) {
                beginCountry.setText("Nederland");
            } else {
                beginCountry.setText(records[0][4]);
            }

            endCity.setText(records[1][3]);

            if (records[1][4].equals("Netherlands")) {
                endCountry.setText("Nederland");
            } else {
                endCountry.setText(records[1][4]);
            }
            kilometersKilometers.setText(records[2][0].replaceAll(",", ""));
            emptyValues.setVisible(false);
        } catch (Exception e) {
            emptyValues.setVisible(true);
        }
    }

    /**
     * HTTP POST request op het klikken van de save knop op declarations
     * @author Richard
     */

    @FXML
    void saveButtonPressed(ActionEvent event) {
        if (beginZipCode.getText().trim().isEmpty() ||
                beginHouseNumber.getText().trim().isEmpty() ||
                beginStreet.getText().trim().isEmpty() ||
                beginCity.getText().trim().isEmpty() ||
                beginCountry.getText().trim().isEmpty() ||
                endZipCode.getText().trim().isEmpty() ||
                endHouseNumber.getText().trim().isEmpty() ||
                endStreet.getText().trim().isEmpty() ||
                endCity.getText().trim().isEmpty() ||
                endCountry.getText().trim().isEmpty() ||
                kilometerDescription.getText().trim().isEmpty()) {
                emptyValues.setVisible(true);
        } else {
            int ownerID = User.getUserID();
            String description = kilometerDescription.getText();
            double kilometers = Double.parseDouble(kilometersKilometers.getText());
            double compensation = Double.parseDouble(kilometersCompensation.getText());
            String beginZip = beginZipCode.getText();
            String beginHouse = beginHouseNumber.getText();
            String beginStr = beginStreet.getText();
            String beginCty = beginCity.getText();
            String beginCtry = beginCountry.getText();
            String endZip = endZipCode.getText();
            String endHouse = endHouseNumber.getText();
            String endStr = endStreet.getText();
            String endCty = endCity.getText();
            String endCtry = endCountry.getText();
            try {
                incorrectDate.setVisible(false);

                try {
                    emptyValues.setVisible(false);
                    Declaration declaration = new Declaration(
                            ownerID, description, kilometers, compensation, beginZip, beginHouse, beginStr, beginCty, beginCtry, endZip, endHouse, endStr, endCty, endCtry);
                    httpRequestHandler.postHandler("/declaration/create", declaration);
                    Node node = (Node)event.getSource();
                    appController.changeView("Declaration", node, "inherit");
                } catch (Exception ignored) {
                }
            }catch(Exception e) {
                incorrectDate.setVisible(true);
            }
        }
    }

    public void getTodaysDate(){
        Date today = Calendar.getInstance().getTime();
        LocalDate date = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dateDate.setValue(date);
    }

    private void automaticKilometerCalculationHandler(){
        automaticKilometer(kilometersCompensation, incorrectCompensation, "compensation");
        automaticKilometer(kilometersKilometers, incorrectKilometers, "kilometer");
    }

    private void automaticKilometer(TextField textField, Text error, String KMorCOM){
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            String kilometersCompensationText = kilometersCompensation.getText();
            String kilometersKilometersText = kilometersKilometers.getText();
            if (kilometersCompensationText.contains(",") || kilometersKilometersText.contains(",")){
                kilometersCompensation.setText(kilometersCompensationText.replace(",", "."));
                kilometersKilometers.setText(kilometersKilometersText.replace(",", "."));
            }
            try{
                error.setVisible(false);
                switch (KMorCOM) {
                    case "compensation":
                        double kilometersCompensation = Double.parseDouble(kilometersCompensationText);
                        compensationPerKilometer.setText(new DecimalFormat("#0.00").format(Double.valueOf(kilometersCompensation)));
                    case "kilometer":
                        double kilometers = Double.parseDouble(kilometersKilometersText);
                        kilometersTraveled.setText((new DecimalFormat("#0.0").format(Double.valueOf(kilometers))));
                }
                calculateDeclaration();
            }catch (NumberFormatException NFe){
                error.setVisible(true);
            }
        });
    }

    private void calculateDeclaration(){
        try {
            String kilometersCompensationText = kilometersCompensation.getText();
            double kilometers = Double.parseDouble(kilometersKilometers.getText());
            double kilometersCompensation = Double.parseDouble(kilometersCompensationText);
            totalCompensation.setText(new DecimalFormat("###0.00").format(kilometers * kilometersCompensation));
        }catch (Exception ignored){
            //dit geeft een error als beide waardes nog niet zijn ingevuld. printen van de stacktrace is niet nodig.
            }
    }

    public void createNewProject(ActionEvent event) {
        Node node = (Node)event.getSource();
        popupController.DisplayPopup("CreateProject", node);
    }

    public void createNewClient(ActionEvent event) {
        Node node = (Node)event.getSource();
        popupController.DisplayPopup("CreateClient", node);
    }

    public void createNewCar(ActionEvent event) {
        Node node = (Node)event.getSource();
        popupController.DisplayPopup("CreateCar", node);
    }

    @FXML
    public void cancelDeclaration(ActionEvent event) {
        Node node = (Node)event.getSource();
        appController.changeView("Declaration", node, "inherit");
    }

    private void checkAddressHandler() {
        checkAddressChecker(beginHouseNumber);
        checkAddressChecker(beginZipCode);
        checkAddressChecker(beginStreet);
        checkAddressChecker(endHouseNumber);
        checkAddressChecker(endZipCode);
        checkAddressChecker(endStreet);
    }

    private void checkAddressChecker(TextField textField) {
        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!beginHouseNumber.getText().trim().isEmpty() && !beginZipCode.getText().trim().isEmpty() && !beginStreet.getText().trim().isEmpty() && !endHouseNumber.getText().trim().isEmpty() && !endZipCode.getText().trim().isEmpty() && !endStreet.getText().trim().isEmpty() && !textField.isFocused()) {
                checkAddress();
                beginCity.setDisable(false);
                beginCountry.setDisable(false);
                endCity.setDisable(false);
                endCountry.setDisable(false);
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        automaticKilometerCalculationHandler();
        checkAddressHandler();
        beginCity.setDisable(true);
        beginCountry.setDisable(true);
        endCity.setDisable(true);
        endCountry.setDisable(true);
        getTodaysDate();
        googleMaps.setCenter(mapImage);
        mapImage.setImage(googleMapsHandler.defaultMap());
    }

}
