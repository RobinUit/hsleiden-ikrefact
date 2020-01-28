package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import sample.Models.Declaration;
import sample.Models.User;
import sample.Services.HTTPRequestHandler;
import java.net.URL;
import java.util.ResourceBundle;

public class DeclarationController implements Initializable {

    @FXML
    private AnchorPane applicationPane;
    @FXML
    private CheckBox selectAllCheck, select1Check, select2Check, select3Check, select4Check, select5Check, select6Check, select7Check, select8Check, select9Check, select10Check;
    @FXML
    private Label val1desc, val2desc, val3desc, val4desc, val5desc, val6desc, val7desc, val8desc, val9desc, val10desc;
    @FXML
    private Label val1car, val2car, val3car, val4car, val5car, val6car, val7car, val8car, val9car, val10car;
    @FXML
    private Label val1date, val2date, val3date, val4date, val5date, val6date, val7date, val8date, val9date, val10date;
    @FXML
    private Label val1km, val2km, val3km, val4km, val5km, val6km, val7km, val8km, val9km, val10km;
    @FXML
    private Label val1amount, val2amount, val3amount, val4amount, val5amount, val6amount, val7amount, val8amount, val9amount, val10amount;
    @FXML
    private Text totalDeclarationsText;
    @FXML
    private Button closeButton, editButton, deleteButton, duplicateButton, nextPage, prevPage, logoutButton, hideButton, plusButton, profileButton, declarationButton, dashboardButton;

    private HTTPRequestHandler httpRequestHandler = new HTTPRequestHandler();
    private AppController appController = new AppController();

    private int pageIndex = 0;
    private int checkBoxesSelected = 0;
    private int length;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDeclarations();
    }

    @FXML
    public void reloadDeclaration() {
        loadDeclarations();
    }

    private void loadDeclarations() {
        length = initdecs(0);
        setPageIndex(length);

        checkCheckboxesHandler();
        editButton.setDisable(true);
        duplicateButton.setDisable(true);
        deleteButton.setDisable(true);
    }

    private void showADeclaration(Label desc, Label cr, Label dat, Label km, Label am, String description, String car, String date, String kilometers, String amount){
        desc.setText(description);
        cr.setText(car);
        dat.setText(date);
        km.setText(kilometers);
        am.setText(amount);
    }

    private void showIndexedDeclaration(int index, int page, String description, String car, String date, String kilometers, String amount) {
        if (page != 0) {
            index = index-10;
        }
        switch (index) {
            case 1:
                showADeclaration(val1desc, val1car, val1date, val1km, val1amount, description, car, date, kilometers, amount);
                break;
            case 2:
                showADeclaration(val2desc, val2car, val2date, val2km, val2amount, description, car, date, kilometers, amount);
                break;
            case 3:
                showADeclaration(val3desc, val3car, val3date, val3km, val3amount, description, car, date, kilometers, amount);
                break;
            case 4:
                showADeclaration(val4desc, val4car, val4date, val4km, val4amount, description, car, date, kilometers, amount);
                break;
            case 5:
                showADeclaration(val5desc, val5car, val5date, val5km, val5amount, description, car, date, kilometers, amount);
                break;
            case 6:
                showADeclaration(val6desc, val6car, val6date, val6km, val6amount, description, car, date, kilometers, amount);
                break;
            case 7:
                showADeclaration(val7desc, val7car, val7date, val7km, val7amount, description, car, date, kilometers, amount);
                break;
            case 8:
                showADeclaration(val8desc, val8car, val8date, val8km, val8amount, description, car, date, kilometers, amount);
                break;
            case 9:
                showADeclaration(val9desc, val9car, val9date, val9km, val9amount, description, car, date, kilometers, amount);
                break;
            case 10:
                showADeclaration(val10desc, val10car, val10date, val10km, val10amount, description, car, date, kilometers, amount);
                break;
        }
    }

    @FXML
    public void selectAll() {
        if (!selectAllCheck.isSelected()) {
            select1Check.setSelected(false);
            select2Check.setSelected(false);
            select3Check.setSelected(false);
            select4Check.setSelected(false);
            select5Check.setSelected(false);
            select6Check.setSelected(false);
            select7Check.setSelected(false);
            select8Check.setSelected(false);
            select9Check.setSelected(false);
            select10Check.setSelected(false);
        } else {
            select1Check.setSelected(true);
            select2Check.setSelected(true);
            select3Check.setSelected(true);
            select4Check.setSelected(true);
            select5Check.setSelected(true);
            select6Check.setSelected(true);
            select7Check.setSelected(true);
            select8Check.setSelected(true);
            select9Check.setSelected(true);
            select10Check.setSelected(true);
        }
    }

    private void checkCheckboxesHandler() {
        checkCheckboxes(select1Check);
        checkCheckboxes(select2Check);
        checkCheckboxes(select3Check);
        checkCheckboxes(select4Check);
        checkCheckboxes(select5Check);
        checkCheckboxes(select6Check);
        checkCheckboxes(select7Check);
        checkCheckboxes(select8Check);
        checkCheckboxes(select9Check);
        checkCheckboxes(select10Check);
    }

    private void checkCheckboxes(CheckBox checkBox){
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                checkBoxesSelected +=1;
            } else {
                checkBoxesSelected -=1;
            }

            if (checkBoxesSelected == 1) {
                editButton.setDisable(false);
                duplicateButton.setDisable(false);
                deleteButton.setDisable(false);
            } else if (checkBoxesSelected > 1){
                editButton.setDisable(true);
                duplicateButton.setDisable(true);
                deleteButton.setDisable(false);
            } else {
                editButton.setDisable(true);
                duplicateButton.setDisable(true);
                deleteButton.setDisable(true);
            }
        });
    }

    private int initdecs(int page) {
        Declaration[] declarations = httpRequestHandler.getDeclarationsByID("/declaration/getDeclarationsByOwnerID/" + User.getUserID());
        for (int i = page; i < declarations.length; i++) {
            showIndexedDeclaration(i+1,
                    page,
                    declarations[i].getDescription(),
                    declarations[i].getOriginZipcode().toUpperCase(),
                    declarations[i].getDestinationZipcode().toUpperCase(),
                    Double.toString(declarations[i].getDeclaredKilometers()),
                    Double.toString(declarations[i].getDeclaredCompensation() * declarations[i].getDeclaredKilometers()));
        }
        return declarations.length;
    }

    private void resetdecs(){
        for (int i = 0; i < 10; i++) {
            showIndexedDeclaration(i+1, 0,"", "", "", "", "");
        }
    }

    public void nextPage(){
        pageIndex+=10;
        try{
            resetdecs();
            initdecs(pageIndex);
            setPageIndex(length);
        } catch (Exception ignored){}
    }

    public void prevPage(){
        if(pageIndex != 0){
            pageIndex-=10;
            try{
                resetdecs();
                initdecs(pageIndex);
                setPageIndex(length);
            } catch (Exception ignored){}
        }
    }

    private void setPageIndex(int lenght) {
        try {
            if (pageIndex + 9 > lenght) {
                if (lenght == 0) {
                    totalDeclarationsText.setText(pageIndex + " - " + lenght + " van " + lenght);
                } else {
                    totalDeclarationsText.setText(pageIndex + 1 + " - " + lenght + " van " + lenght);
                }
                nextPage.setDisable(true);
            } else {
                totalDeclarationsText.setText(pageIndex+1 + " - " + (pageIndex + 10) + " van " + lenght);
                nextPage.setDisable(false);
            }

            if (pageIndex == 0) {
                prevPage.setDisable(true);
            } else {
                prevPage.setDisable(false);
            }
        } catch (Exception ignored) {}
    }

    @FXML
    public void edit() {
        //niet geïmplementeerd
    }

    @FXML
    public void delete() {
        //niet geïmplementeerd
    }

    @FXML
    public void duplicate() {
        //niet geïmplementeerd
    }

    @FXML
    public void openDashboard() {
        appController.changeView("Dashboard", applicationPane, "inherit");
    }

    @FXML
    public void openProfile() {
        appController.changeView("Profile", applicationPane, "inherit");
    }

    @FXML
    public void openCreateDeclaration() {
        appController.changeView("CreateDeclaration", applicationPane, "inherit");
    }

    @FXML
    public void logout() {
        appController.logout(applicationPane);
    }

    @FXML
    public void minimizeApplication() {
        appController.minimizeApplication(applicationPane);
    }

    @FXML
    public void closeApplication() {
        appController.closeApplication(applicationPane);
    }
}
