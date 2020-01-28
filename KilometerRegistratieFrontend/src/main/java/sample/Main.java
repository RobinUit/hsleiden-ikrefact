package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Utility.WindowStyle;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    private Parent applicationView;
    private Scene applicationScene;
    private Stage applicationStage;

    @Override
    public void start(Stage applicationStage) {
        this.applicationStage = applicationStage;

        setupView();
        setupScene();
        setupStage(this.applicationStage);

        applicationStage.show();
    }

    private void setupView() {
        try {
            applicationView = FXMLLoader.load(getClass().getResource("/FXML/LoginView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupScene() {
        applicationScene = new Scene(applicationView);
        applicationScene.setFill(Color.TRANSPARENT);
    }

    private void setupStage(Stage applicationStage) {
        WindowStyle windowStyle = new WindowStyle();

        windowStyle.enableStageDrag(applicationView, applicationStage);

        applicationStage.initStyle(StageStyle.TRANSPARENT);
        applicationStage.setTitle("Kilometer Registratie");

        applicationStage.setScene(applicationScene);

        setApplicationIcon();
    }

    private void setApplicationIcon() {
        try {
            URL iconURL = Main.class.getResource("/img/AppIcon.png");
            java.awt.Image image = new ImageIcon(iconURL).getImage();
            com.apple.eawt.Application.getApplication().setDockIconImage(image);
        } catch (Exception ignored) {
            applicationStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/AppIcon.png")));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
