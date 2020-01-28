package sample.Controllers.Popups;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import sample.Models.Car;
import sample.Models.User;
import sample.Services.HTTPRequestHandler;
import sample.Utility.ActiveCar;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateCarPopupController implements Initializable {

    @FXML
    private AnchorPane popupPane;
    @FXML
    private Button closeButton, saveButton;
    @FXML
    private Text titlePopup;
    @FXML
    private TextField nameField, licenseField, brandField, typeField, colorField, fuelTypeField;

    private HTTPRequestHandler httpRequestHandler = new HTTPRequestHandler();
    private PopupController popupController = new PopupController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (ActiveCar.getActiveCar() != null) {
            loadActiveCar();
        }
    }

    private void loadActiveCar() {
        Car activeCar = ActiveCar.getActiveCar();

        titlePopup.setText("Auto aanpassen");
        licenseField.setText(activeCar.getLicensePlate());
        nameField.setText(activeCar.getName());
        brandField.setText(activeCar.getBrand());
        typeField.setText(activeCar.getType());
        colorField.setText(activeCar.getColor());
        fuelTypeField.setText(activeCar.getFuelType());

        licenseField.setDisable(true);
    }

    @FXML
    void saveCar() {

        if (!fieldsAreCorrect()) {
            return;
        }

        try {
            Car car = createCar();
            httpRequestHandler.postHandler("/car/create", car);
            closePopup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean fieldsAreCorrect() {
        int LENGHT_LICENSEPLATE = 8;

        if (licenseField.getText().length() != LENGHT_LICENSEPLATE) {
            licenseField.setText("Onjuist kenteken.");
            return false;
        } else if (
                licenseField.getText().trim().isEmpty() ||
                        nameField.getText().trim().isEmpty() ||
                        brandField.getText().trim().isEmpty() ||
                        typeField.getText().trim().isEmpty() ||
                        colorField.getText().trim().isEmpty() ||
                        fuelTypeField.getText().trim().isEmpty()
        ) {
            return false;
        }
        return true;
    }

    private Car createCar() {
        return new Car(
                licenseField.getText(),
                User.getUserID(),
                nameField.getText(),
                brandField.getText(),
                typeField.getText(),
                colorField.getText(),
                fuelTypeField.getText()
        );
    }

    @FXML
    void closePopup() {
        popupController.closePopup(popupPane);
    }

}