package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.Models.User;
import sample.Services.HTTPRequestHandler;

public class CreateAccountController {

    @FXML
    public AnchorPane applicationPane;
    @FXML
    public Button createAccountButton, signUpButton, closeButton, hideButton;
    @FXML
    private TextField emailField, nameField, passwordField, repeatedPasswordField;
    @FXML
    private Label errorLabel;

    private HTTPRequestHandler httpRequestHandler = new HTTPRequestHandler();
    private AppController appController = new AppController();

    private String email;
    private String name;
    private String password;
    private String repeatedPassword;

    @FXML
    private void attemptCreateAccount() {
        email = emailField.getText().trim().toLowerCase();
        name = nameField.getText().trim();
        password = passwordField.getText().trim();
        repeatedPassword = repeatedPasswordField.getText().trim();

        if (fieldsAreFilledIn() && passwordsMatch() && emailIsValid()) {
            createAccount();
        }
    }

    private boolean fieldsAreFilledIn() {
        if (!emailField.getText().trim().isEmpty() &&
                !nameField.getText().trim().isEmpty() &&
                !passwordField.getText().trim().isEmpty() &&
                !repeatedPasswordField.getText().trim().isEmpty()) {
            return true;
        }
        errorLabel.setText("Niet alles ingevuld");
        return false;
    }

    private boolean passwordsMatch() {
        if (password.equals(repeatedPassword)) {
            return true;
        }
        errorLabel.setText("Wachtwoorden komen niet overeen");
        return false;
    }

    private boolean emailIsValid() {
        if (emailField.getText().contains("@") || emailField.getText().contains(".")) {
            return true;
        }
        errorLabel.setText("Vul een geldig email adres in.");
        return false;
    }

    private void createAccount() {
        User user = new User(email, name, password);
        try {
            httpRequestHandler.postHandler("/user/create", user);
            appController.changeView("Login", applicationPane, "inherit");
        } catch (Exception e) {
            errorLabel.setText("E-mail adres bestaat al");
        }
    }

    @FXML
    private void back() {
        appController.changeView("Login", applicationPane, "inherit");
    }

    @FXML
    private void minimizeStage() {
        appController.minimizeApplication(applicationPane);
    }

    @FXML
    private void closeStage() {
        appController.closeApplication(applicationPane);
    }
}