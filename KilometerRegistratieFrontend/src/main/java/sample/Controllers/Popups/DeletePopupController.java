package sample.Controllers.Popups;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class DeletePopupController {

    @FXML
    private AnchorPane popupPane;
    @FXML
    private Button acceptButton, cancelButton;

    private PopupController popupController = new PopupController();

    @FXML
    public void closePopup() {
        popupController.closePopup(popupPane);
    }

    @FXML
    public void delete() {
    }
}
