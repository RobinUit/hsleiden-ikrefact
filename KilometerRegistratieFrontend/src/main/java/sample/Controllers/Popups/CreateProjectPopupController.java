package sample.Controllers.Popups;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Models.Project;
import sample.Models.User;
import sample.Services.HTTPRequestHandler;
import java.time.LocalDate;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CreateProjectPopupController {

    @FXML
    private AnchorPane popupPane;
    @FXML
    private Button saveButton, closeButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private DatePicker beginDateField, endDateField;

    private HTTPRequestHandler httpRequestHandler = new HTTPRequestHandler();
    private PopupController popupController = new PopupController();

    @FXML
    void save(ActionEvent event) throws Exception {
        int ownerID = User.getUserID();
        String name = nameField.getText();
        String description =  descriptionField.getText();
        LocalDate beginLocalDate = beginDateField.getValue();
        LocalDate endLocalDate = endDateField.getValue();
        if (beginLocalDate == null || endLocalDate == null) {
            return;
        } else if (endLocalDate.isBefore(beginLocalDate)) {
            return;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String beginDate = beginLocalDate.format(formatter);
            String endDate = endLocalDate.format(formatter);

            Project project = new Project(ownerID, name, description, beginDate, endDate);
            httpRequestHandler.postHandler("/project/create", project);
            closePopup();
        }
    }

    @FXML
    void closePopup() {
        popupController.closePopup(popupPane);
    }
}
