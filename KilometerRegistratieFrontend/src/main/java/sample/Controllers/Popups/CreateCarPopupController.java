package sample.Controllers.Popups;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Models.Car;
import sample.Models.User;
import sample.Services.HTTPRequestHandler;
import sample.Utility.ActiveCar;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateCarPopupController implements Initializable {

    private HTTPRequestHandler httpRequestHandler = new HTTPRequestHandler();

    @FXML
    private Text titelPopup;

    @FXML
    private TextField nameField, licenseField, brandField, typeField, colorField, fuelTypeField;

    @FXML
    void close(ActionEvent event) {
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save(ActionEvent event) throws Exception {
        if(licenseField.getText().length()<9){
            String licensePlate = licenseField.getText();
            int ownerID = User.getUserID();
            String name =  nameField.getText();
            String merk = brandField.getText();
            String type = typeField.getText();
            String color = colorField.getText();
            String fuelType = fuelTypeField.getText();

            Car car = new Car(licensePlate,ownerID, name, merk, type, color, fuelType);
            httpRequestHandler.postHandler("/car/create", car);
            //close
            Node node = (Node)event.getSource();
            Stage stage = (Stage)node.getScene().getWindow();
            stage.close();
        }else{
            licenseField.setText("Onjuist kenteken.");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(ActiveCar.getAuto()!=null){
            titelPopup.setText("Auto aanpassen");
            Car localCar = ActiveCar.getAuto();
            licenseField.setText(localCar.getLicencePlate());
            licenseField.setDisable(true); // //toevoegen?
            nameField.setText(localCar.getCarName());
            brandField.setText(localCar.getCarBrand());
            typeField.setText(localCar.getCarType());
            colorField.setText(localCar.getCarColor());
            fuelTypeField.setText(localCar.getFuelType());
        }
    }
}