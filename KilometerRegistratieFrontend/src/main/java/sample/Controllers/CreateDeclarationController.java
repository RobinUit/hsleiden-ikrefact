package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import sample.Controllers.Popups.PopupController;
import sample.Models.Declaration;
import sample.Models.User;
import sample.Services.GoogleMapsHandler;
import sample.Services.HTTPRequestHandler;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.ResourceBundle;

public class CreateDeclarationController implements Initializable {

    @FXML
    private AnchorPane applicationPane;
    @FXML
    public TextField originZipcodeTextfield, originHousenumberTextfield, originStreetTextfield, originCityTextfield, originCountryTextfield, destinationZipcodeTextfield, destinationHousenumberTextfield, destinationStreetTextfield, destinationCityTextfield, destinationCountryTextfield, declaredKilometersTextfield, declaredCompensationTextfield;
    @FXML
    public DatePicker dateField;
    @FXML
    public Text incorrectKilometers, incorrectCompensation, incorrectDate, kilometersTraveled, compensationPerKilometer, totalCompensation;
    @FXML
    public BorderPane googleMaps;
    @FXML
    public ImageView mapImage;
    @FXML
    public Button closeButton, newProjectButton, newClientButton, newCarButton, saveButton;
    @FXML
    public TextArea descriptionField;
    @FXML
    public Label emptyValues;
    @FXML
    private ComboBox kilometersProject, kilometersClient, kilometersCar;

    private HTTPRequestHandler httpRequestHandler = new HTTPRequestHandler();
    private GoogleMapsHandler googleMapsHandler = new GoogleMapsHandler();
    private AppController appController = new AppController();
    private PopupController popupController = new PopupController();

    private String originZipcode, originHousenumber, originStreet, originCity, originCountry;
    private String destinationZipcode, destinationHousenumber, destinationStreet, destinationCity, destinationCountry;
    private String declaredKilometers, declaredCompensation;
    private String description;

    private String targetedField;
    private Text errorField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupEventListeners();
        disableTextfields(true);
        setTodaysDate();
        mapImage.setImage(googleMapsHandler.getDefaultMap());
    }

    private void setupEventListeners() {
        textfieldFocusedEventListener(originHousenumberTextfield, "checkAddress");
        textfieldFocusedEventListener(originZipcodeTextfield, "checkAddress");
        textfieldFocusedEventListener(originStreetTextfield, "checkAddress");
        textfieldFocusedEventListener(destinationHousenumberTextfield, "checkAddress");
        textfieldFocusedEventListener(destinationZipcodeTextfield, "checkAddress");
        textfieldFocusedEventListener(destinationStreetTextfield, "checkAddress");
        textfieldFocusedEventListener(declaredCompensationTextfield, "calculateTotalCompensation");
        textfieldFocusedEventListener(declaredKilometersTextfield, "calculateTotalCompensation");
    }

    private void textfieldFocusedEventListener(TextField textField, String function) {
        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            switch (function) {
                case "checkAddress":
                    automaticallygetFullAddressAndDistance();
                    break;
                case "calculateTotalCompensation":
                    automaticallyCalculateTotalCompensation(textField);
                    break;
                default:
            }
        });
    }

    private void automaticallygetFullAddressAndDistance() {
        if (checkForEmptyValues(false)) {
            getFullAddressAndDistance();
            disableTextfields(false);
        }
    }

    private void getFullAddressAndDistance() {
        storeAllInputValues();

        if (checkForEmptyValues(true)) {
            return;
        }

        try {
            String origin = originHousenumber + "+" + originStreet.replaceAll(" ", "+") + "," + originZipcode;
            String destination = destinationHousenumber + "+" + destinationStreet.replaceAll(" ", "+") + "," + destinationZipcode;

            String[][] records = googleMapsHandler.getOriginDestinationAddressAndDistanceBetween(origin, destination);
            mapImage.setImage(googleMapsHandler.getRouteMap(origin, destination));

            originCityTextfield.setText(records[0][3]);
            originCountryTextfield.setText(records[0][4]);
            destinationCityTextfield.setText(records[1][3]);
            destinationCountryTextfield.setText(records[1][4]);

            declaredKilometersTextfield.setText(records[2][0]);
            emptyValues.setVisible(false);
        } catch (Exception e) {
            emptyValues.setVisible(true);
        }
    }

    private void automaticallyCalculateTotalCompensation(TextField textField) {
        setCorrectVariables(textField);
        replaceCommaForDot();
        storeAllInputValues();
        if (errorField == null || targetedField == null) {
            return;
        }
        formatTextfieldValues();
    }

    private void setCorrectVariables(TextField textField) {
        if (textField.getId().contains("Kilometer")) {
            targetedField = "kilometer";
            errorField = incorrectKilometers;
        } else if (textField.getId().contains("Compensation")) {
            targetedField = "compensation";
            errorField = incorrectCompensation;
        }
    }

    private void replaceCommaForDot() {
        if (declaredCompensation.contains(",") || declaredKilometers.contains(",")) {
            declaredCompensationTextfield.setText(declaredCompensation.replace(",", "."));
            declaredKilometersTextfield.setText(declaredKilometers.replace(",", "."));
        }
    }

    private void formatTextfieldValues() {
        try {
            errorField.setVisible(false);
            switch (targetedField) {
                case "compensation":
                    compensationPerKilometer.setText(new DecimalFormat("#0.00").format(
                            Double.valueOf(declaredCompensation)));
                case "kilometer":
                    kilometersTraveled.setText(new DecimalFormat("#0.0").format(
                            Double.valueOf(declaredKilometers))
                    );
            }
            calculateTotalCompensation();

        } catch (NumberFormatException NFe) {
            errorField.setVisible(true);
        }
    }

    private void calculateTotalCompensation() {
        try {
            totalCompensation.setText(new DecimalFormat("###0.00").format(
                    Double.parseDouble(declaredCompensation) * Double.parseDouble(declaredKilometers)));
        } catch (Exception ignored) {
            //returns an error when both values are not filled in (yet)
        }
    }

    private void disableTextfields(boolean disabled) {
        originCityTextfield.setDisable(disabled);
        originCountryTextfield.setDisable(disabled);
        destinationCityTextfield.setDisable(disabled);
        destinationCountryTextfield.setDisable(disabled);
    }

    @FXML
    public void setTodaysDate() {
        //returns the current date in YYYY-MM-DD
        LocalDate todaysDate = Calendar.getInstance().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        dateField.setValue(todaysDate);
    }

    private boolean checkForEmptyValues(boolean checkAllFields) {
        storeAllInputValues();

        if (firstFieldsEmpty()) {
            emptyValues.setVisible(true);
            return false;
        }

        if (checkAllFields) {
            if (secondFieldsEmpty()) {
                emptyValues.setVisible(true);
                return false;
            }
        }

        emptyValues.setVisible(false);

        return true;
    }

    private boolean firstFieldsEmpty() {
        return originHousenumber.isEmpty() ||
                originStreet.isEmpty() ||
                originZipcode.isEmpty() ||
                destinationHousenumber.isEmpty() ||
                destinationStreet.isEmpty() ||
                destinationZipcode.isEmpty();
    }

    private boolean secondFieldsEmpty() {
        return originCity.isEmpty() ||
                originCountry.isEmpty() ||
                destinationCity.isEmpty() ||
                destinationCountry.isEmpty() ||
                declaredCompensation.isEmpty() ||
                declaredKilometers.isEmpty() ||
                description.length() != 0;
    }

    @FXML
    void attemptSaveDeclaration() {
        if (checkForEmptyValues(true)) {
            emptyValues.setVisible(true);
            return;
        }

        saveDeclaration();
    }

    private void saveDeclaration() {
        try {
            emptyValues.setVisible(false);
            Declaration declaration = createDeclaration();
            httpRequestHandler.postHandler("/declaration/create", declaration);
            appController.changeView("Declaration", applicationPane, "inherit");
        } catch (Exception e) {
            emptyValues.setVisible(true);
        }
    }

    private void storeAllInputValues() {
        this.originZipcode = originZipcodeTextfield.getText().trim();
        this.originHousenumber = originHousenumberTextfield.getText().trim();
        this.originStreet = originStreetTextfield.getText().trim();
        this.originCity = originCityTextfield.getText().trim();
        this.originCountry = originCountryTextfield.getText().trim();
        this.destinationZipcode = destinationZipcodeTextfield.getText().trim();
        this.destinationHousenumber = destinationHousenumberTextfield.getText().trim();
        this.destinationStreet = destinationStreetTextfield.getText().trim();
        this.destinationCity = destinationCityTextfield.getText().trim();
        this.destinationCountry = destinationCountryTextfield.getText().trim();
        this.declaredKilometers = declaredKilometersTextfield.getText().trim();
        this.declaredCompensation = declaredCompensationTextfield.getText().trim();
        this.description = descriptionField.getText().trim();
    }

    private Declaration createDeclaration() {
        return new Declaration(
                User.getUserID(),
                description,
                Double.parseDouble(declaredKilometers),
                Double.parseDouble(declaredCompensation),
                originZipcode,
                originHousenumber,
                originStreet,
                originCity,
                originCountry,
                destinationZipcode,
                destinationHousenumber,
                destinationStreet,
                destinationCity,
                destinationCountry);
    }

    @FXML
    public void createNewProject() {
        popupController.openPopup("CreateProject", applicationPane);
    }

    @FXML
    public void createNewClient() {
        popupController.openPopup("CreateClient", applicationPane);
    }

    @FXML
    public void createNewCar() {
        popupController.openPopup("CreateCar", applicationPane);
    }

    @FXML
    public void cancelDeclaration() {
        appController.changeView("Declaration", applicationPane, "inherit");
    }
}
