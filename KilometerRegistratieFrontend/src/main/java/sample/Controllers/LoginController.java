package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Models.User;
import sample.Services.HTTPRequestHandler;

public class LoginController {

    private AppController appController = new AppController();
    private HTTPRequestHandler httpRequestHandler = new HTTPRequestHandler();

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button closeButton, hideButton;

    @FXML
    private Label loginLabel;

    @FXML
    void login(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();
        if (email.trim().isEmpty() || password.trim().isEmpty()) {
            loginLabel.setText("Geen mailadres of wachtwoord ingevuld");
            return;
        }
        try{
            User u = httpRequestHandler.getUserByMail("/user/get/" + email);
            if(u.getPassword().equals(passwordField.getText())){
                Node node = (Node)event.getSource();
                User.setUserID(httpRequestHandler.getUserIDByEmail("/user/getUserID/" + email));
                appController.changeView("Dashboard", node, "center");
            } else {
                loginLabel.setText("Incorrect mailadres of wachtwoord ingevuld");
            }
        } catch(Exception e){
            loginLabel.setText("Incorrect mailadres of wachtwoord ingevuld");
        }
    }

    @FXML
    void signUp(ActionEvent event) {
        Node node = (Node)event.getSource();
        appController.changeView("CreateAccount", node, "inherit");
    }

    @FXML
    void close() {
        Stage stage = (Stage)closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void hide() {
        Stage stage = (Stage)hideButton.getScene().getWindow();
        stage.setIconified(true);
    }
}