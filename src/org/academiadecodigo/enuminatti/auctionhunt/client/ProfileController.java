package org.academiadecodigo.enuminatti.auctionhunt.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.academiadecodigo.enuminatti.auctionhunt.server.ServiceRegistry;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable, Controller {

    private CommunicationService communicationService;
    @FXML
    private TabPane secondScene;

    @FXML
    private Label photo;

    @FXML
    private ImageView labelAvatar;

    @FXML
    private Button logoutButton;

    @FXML
    private Button goToAuctionButton;

    @FXML
    private Button depositButton;

    @FXML
    private TextField insertWithdrawMoney;

    @FXML
    private Button withdrawButton;

    @FXML
    private Label fundsAvailable;

    @FXML
    private Label username;

    @FXML
    private Button uploadItemButton;

    @FXML
    private TextField uploadImageDirectory;

    @FXML
    private Button okUpload;

    @FXML
    private TextField itemNameField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField priceField;

    @FXML
    private Label notOkSubmit;

    @FXML
    private Label okSubmit;

    @FXML
    private AnchorPane UploadPhotoButton;

    @FXML
    private Button UploadPhoto;


    @FXML
    private TextField InsertPathforPhotoUser;

    @FXML
    void onDepositButtonPressed(ActionEvent event) {

        String money = insertWithdrawMoney.getText();

        try {
            Integer.parseInt(money);
        } catch (NumberFormatException e) {
            return;
        }

        if (Integer.parseInt(money) > 999999) {
            insertWithdrawMoney.setText("");
            return;
        }


        transferMoney(money, depositButton.getText());

    }

    /**
     * @param event
     */
    @FXML
    void onGoToAuctionButtonPressed(ActionEvent event) {


        //String decodeMessage = ParseClient.getInstance().receiveDataServer(receiveHead);

        Navigation.getInstance().loadScreen("bidAuction");
        return;


    }

    /**
     * @param event
     */
    @FXML
    void onLogoutButtonPressed(ActionEvent event) {
        Navigation.getInstance().back();
        Navigation.getInstance().back();
        Navigation.getInstance().loadScreen("login&register");
    }

    /**
     * @param event
     */
    @FXML
    void onMyFundsButtonPressed(ActionEvent event) {

    }

    /**
     * @param event
     */
    @FXML
    void onUploadButtonPressed(ActionEvent event) {

        FileChooser chooser = new FileChooser();
        System.out.println(chooser);
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selected = chooser.showOpenDialog(Navigation.root.getScene().getWindow());
        Window file = Navigation.root.getScene().getWindow();
        System.out.println(selected);

        if (selected != null) {
            selected.getAbsolutePath();
            System.out.println("Uploading file: " + selected.getAbsolutePath());
            uploadImageDirectory.setText(selected.getAbsolutePath());
        }

    }

    /**
     * @param event
     */
    @FXML
    void onUploadPhotoButtonPressed(ActionEvent event) {
    }

    /**
     * @param event
     */
    @FXML
    void onWithdrawButtonPressed(ActionEvent event) {

        String money = insertWithdrawMoney.getText();

        try {
            Integer.parseInt(money);
        } catch (NumberFormatException e) {
            return;
        }

        if (Integer.parseInt(money) > Integer.parseInt(fundsAvailable.getText())) {
            return;
        }

        if (Integer.parseInt(money) > 999999) {
            insertWithdrawMoney.setText("");
            return;
        }

        transferMoney(money, withdrawButton.getText());

    }

    private void transferMoney(String money, String buttonText) {

        String moneyAndHead = ParseClient.getInstance().setDataServer(money, buttonText);
        communicationService.sendData(moneyAndHead);
    }

    @FXML
    void onButtonPressedCancelUpload(ActionEvent event) {

    }

    @FXML
    void onButtonPressedOkPressed(ActionEvent event) {

        if (!checkValues()) {
            return;
        }
        notOkSubmit.setVisible(false);

        String username = ParseClient.getInstance().getUserName();

        String data = username + "#" + itemNameField.getText() + "#" + descriptionField.getText() + "#" + priceField.getText();

        String dataAndHead = ParseClient.getInstance().setDataServer(data, okUpload.getText());

        String path = uploadImageDirectory.getText();

        System.out.println("PATH:" + path);
        System.out.println("DATA:" + dataAndHead);
        communicationService.uploadImage(path, dataAndHead);
    }

    private boolean checkValues() {

        if (itemNameField.getText().equals("")) {
            notOkSubmit.setVisible(true);
            notOkSubmit.setText("Item name");
            return false;
        }
        if (descriptionField.getText().equals("")) {
            notOkSubmit.setVisible(true);
            notOkSubmit.setText("Item description!");
            return false;
        }
        if (priceField.getText().equals("")) {
            notOkSubmit.setVisible(true);
            notOkSubmit.setText("Item price ");
            return false;
        }
        try {
            Integer.parseInt(priceField.getText());
        } catch (NumberFormatException a) {
            notOkSubmit.setText("Insert a value");
            return false;
        }

        if (Integer.parseInt(priceField.getText()) > 999999) {
            notOkSubmit.setVisible(true);
            notOkSubmit.setText("Not permited value");
            return false;
        }
        if(uploadImageDirectory.getText().equals("")){
            notOkSubmit.setVisible(true);
            notOkSubmit.setText("Choose some file");
            return false;
        }
        return true;
    }

    /**
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        communicationService = (CommunicationService) ServiceRegistry.getInstance().getService("CommunicationService");
        showValues();
    }

    private void showValues() {
        fundsAvailable.setText(ParseClient.getInstance().getUserFunds());
        username.setText(ParseClient.getInstance().getUserName());
        okSubmit.setVisible(false);
        notOkSubmit.setVisible(false);
        labelAvatar.setImage(new Image("avatar.png"));
    }

    public void changeView(String string) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                switch (string) {

                    case "deposit":
                    case "withdraw":
                        fundsAvailable.setText(ParseClient.getInstance().getFunds());
                        break;
                    case "item":
                        okSubmit.setVisible(true);
                        itemNameField.setText("");
                        priceField.setText("");
                        descriptionField.setText("");
                        uploadImageDirectory.setText("");
                        break;
                    default:
                        System.out.println("Cenas by Aires, try again");
                }
            }
        });
    }

}

