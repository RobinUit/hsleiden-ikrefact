package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Models.User;
import sample.Services.HTTPRequestHandler;

public class CreateAccountController{

    private AppController appController = new AppController();
    private HTTPRequestHandler httpRequestHandler = new HTTPRequestHandler();

    @FXML
    TextField emailField, nameField, passwordField, password2Field;

    @FXML
    private Label errorLabel;

    @FXML
    private Button closeButton, hideButton;

    @FXML
    public void newAccount(ActionEvent event){
        if (emailField.getText().trim().isEmpty() || nameField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty() || password2Field.getText().trim().isEmpty()) {
            errorLabel.setText("Niet alles ingevuld");
        }else {
            if(!passwordField.getText().equals(password2Field.getText())){
                errorLabel.setText("Wachtwoorden komen niet overeen");
            } else{
                if(!emailField.getText().contains("@") || !emailField.getText().contains(".")){
                    errorLabel.setText("Vul een geldig email adres in.");
                } else{
                    User user = new User(emailField.getText().toLowerCase(), nameField.getText(), passwordField.getText());
                    try {
                        httpRequestHandler.postHandler("/user/create", user);
                        Node node = (Node)event.getSource();
                        appController.changeView("Login", node, "inherit" );
                    } catch (Exception e) {
                        errorLabel.setText("E-mail adres bestaat al");
                    }
                }

            }
        }
    }

    @FXML
    public void back(ActionEvent event) {
        Node node = (Node)event.getSource();
        appController.changeView("Login", node, "inherit");
    }

    @FXML
    void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void hide() {
        Stage stage = (Stage) hideButton.getScene().getWindow();
        stage.setIconified(true);
    }
}
