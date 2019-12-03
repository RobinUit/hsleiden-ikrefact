package sample.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sample.Utility.WindowStyle;
import java.io.IOException;

class AppController {

    void changeView(String pane, Node node, String position){
        try {
            Parent newView = FXMLLoader.load(getClass().getResource("/FXML/" + pane + "View.fxml"));
            Scene newScene = new Scene(newView);
            newScene.setFill(Color.TRANSPARENT);
            Stage stage = (Stage) node.getScene().getWindow();
            WindowStyle.allowDrag(newView, stage);
            stage.setScene(newScene);

            if (position.equals("center")) {
                centerStage(stage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void centerStage(Stage stage) {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 4);
    }
}
