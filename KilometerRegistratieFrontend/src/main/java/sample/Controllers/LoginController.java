package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.Models.User;
import sample.Services.HTTPRequestHandler;

public class LoginController {

    @FXML
    private AnchorPane applicationPane;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button closeButton, hideButton, signUpButton, loginButton;
    @FXML
    private Label loginLabel;

    private HTTPRequestHandler httpRequestHandler = new HTTPRequestHandler();
    private AppController appController = new AppController();

    private String email;
    private String password;
    private User user;

    @FXML
    void attemptLogin() {
        email = emailField.getText().trim();
        password = passwordField.getText().trim();

        if (fieldsAreFilledIn()) {
            login();
        }
    }

    private boolean fieldsAreFilledIn() {
        if (!email.isEmpty() && !password.isEmpty()) {
            return true;
        }
        loginLabel.setText("Geen mailadres of wachtwoord ingevuld");
        return false;
    }

    private void login() {
        try {
            user = httpRequestHandler.getUserByMail("/user/get/" + email);
            validatePassword();
        } catch (Exception e) {
            loginLabel.setText("Incorrect mailadres of wachtwoord ingevuld");
        }
    }

    private void validatePassword() {
        if (passwordsMatch()) {
            User.setUserID(httpRequestHandler.getUserIDByEmail("/user/getUserID/" + email));
            appController.changeView("Dashboard", applicationPane, "center");
        }
        loginLabel.setText("Incorrect mailadres of wachtwoord ingevuld");
    }

    private boolean passwordsMatch() {
        return user.getPassword().equals(passwordField.getText());
    }

    @FXML
    void signUp() {
        appController.changeView("CreateAccount", applicationPane, "inherit");
    }

    @FXML
    void closeApplication() {
        appController.closeApplication(applicationPane);
    }

    @FXML
    void minimizeApplication() {
        appController.minimizeApplication(applicationPane);
    }
}