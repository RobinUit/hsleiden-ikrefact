package sample.Controllers.Popups;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Utility.WindowStyle;
import java.io.IOException;

public class PopupController {

    public void DisplayPopup(String pane, Node node) {

        try {
            Parent newPopupView = FXMLLoader.load(getClass().getResource("/FXML/" + pane + "Popup.fxml"));

            Scene newPopupScene = new Scene(newPopupView);
            newPopupScene.setFill(Color.TRANSPARENT);
            Stage newPopupstage = new Stage();

            WindowStyle.allowDrag(newPopupView, newPopupstage);

            newPopupstage.initStyle(StageStyle.TRANSPARENT);
            newPopupstage.setScene(newPopupScene);
            newPopupstage.initOwner(node.getScene().getWindow());
            newPopupstage.setResizable(false);
            newPopupstage.alwaysOnTopProperty();

            newPopupstage.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                if (!isNowFocused) {
                    newPopupstage.hide();
                }
            });

            newPopupstage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
