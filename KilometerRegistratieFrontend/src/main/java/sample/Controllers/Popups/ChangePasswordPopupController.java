package sample.Controllers.Popups;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;

public class ChangePasswordPopupController {

    @FXML
    private AnchorPane popupPane;
    @FXML
    private Button closeButton, changePasswordButton;
    @FXML
    private PasswordField oldPasswordField, newPasswordField, newPassword2Field;

    private PopupController popupController = new PopupController();

    @FXML
    private void closePopup() {
        popupController.closePopup(popupPane);
    }

    @FXML
    private void changePassword() {
        //niet geïmplementeerd
        closePopup();
    }

}
