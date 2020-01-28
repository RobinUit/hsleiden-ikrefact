package sample.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sample.Utility.WindowStyle;
import java.io.IOException;

class AppController {

    private AnchorPane applicationPane;
    private Parent applicationView;
    private Scene applicationScene;
    private Stage applicationStage;

    void changeView(String viewName, AnchorPane applicationPane, String position){
        this.applicationPane = applicationPane;

        getStage();
        setupView(viewName);
        setupScene();
        setupStage(position);
    }

    private void setupView(String viewName) {
        try {
            applicationView = FXMLLoader.load(getClass().getResource("/FXML/" + viewName + "View.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupScene() {
        applicationScene = new Scene(applicationView);
        applicationScene.setFill(Color.TRANSPARENT);
    }

    private void setupStage(String position) {
        WindowStyle windowStyle = new WindowStyle();

        windowStyle.enableStageDrag(applicationView, applicationStage);

        applicationStage.setScene(applicationScene);

        if (position.equals("center")) {
            centerStage();
        }
    }

    private void centerStage() {
        int HALF_THE_SCREEN = 2;
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        applicationStage.setX((primScreenBounds.getWidth() - applicationStage.getWidth()) / HALF_THE_SCREEN);
        applicationStage.setY((primScreenBounds.getHeight() - applicationStage.getHeight()) / HALF_THE_SCREEN);
    }

    void logout(AnchorPane applicationPane) {
        changeView("Login", applicationPane, "center");
    }

    void closeApplication(AnchorPane applicationPane) {
        this.applicationPane = applicationPane;
        getStage();
        applicationStage.close();
    }

    void minimizeApplication(AnchorPane applicationPane) {
        this.applicationPane = applicationPane;
        getStage();
        applicationStage.setIconified(true);
    }

    private void getStage() {
        this.applicationStage = (Stage) applicationPane.getScene().getWindow();
    }
}