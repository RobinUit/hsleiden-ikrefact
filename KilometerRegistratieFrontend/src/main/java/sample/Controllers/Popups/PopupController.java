package sample.Controllers.Popups;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Utility.WindowStyle;

import java.io.IOException;

public class PopupController {

    private Parent popupView;
    private Scene popupScene;
    private Stage popupStage;

    public void openPopup(String viewName, AnchorPane applicationPane) {
        setupView(viewName);
        setupScene();
        setupStage(applicationPane);
        popupStage.show();
    }
    
    private void setupView(String viewName) {
        try {
            popupView = FXMLLoader.load(getClass().getResource("/FXML/" + viewName + "Popup.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void setupScene() {
        popupScene = new Scene(popupView);
        popupScene.setFill(Color.TRANSPARENT);
    }
    
    private void setupStage(AnchorPane applicationPane) {
        popupStage = new Stage();
        WindowStyle windowStyle = new WindowStyle();

        windowStyle.enableStageDrag(popupView, popupStage);

        popupStage.initStyle(StageStyle.TRANSPARENT);
        popupStage.setScene(popupScene);
        popupStage.initOwner(applicationPane.getScene().getWindow());
        popupStage.setResizable(false);
        popupStage.alwaysOnTopProperty();

        closePopupOnUnfocus();
    }

    private void closePopupOnUnfocus() {
        popupStage.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                popupStage.close();
            }
        });
    }

    void closePopup(AnchorPane popupPane) {
        Stage popupStage = (Stage) popupPane.getScene().getWindow();
        popupStage.close();
    }
}
