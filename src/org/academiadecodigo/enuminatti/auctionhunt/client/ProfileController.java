package org.academiadecodigo.enuminatti.auctionhunt.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.academiadecodigo.enuminatti.auctionhunt.server.ParseServer;
import org.academiadecodigo.enuminatti.auctionhunt.server.ServiceRegistry;
import org.academiadecodigo.enuminatti.auctionhunt.utils.ItemData;
import org.academiadecodigo.enuminatti.auctionhunt.utils.UserData;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable, Controller {

    private CommunicationService communicationService;
    @FXML
    private Label photo;

    @FXML
    private Button logoutButton;

    @FXML
    private Button goToAuctionButton;

    @FXML
    private Button uploadItemButton;

    @FXML
    private TextField uploadImageDirectory;

    @FXML
    private Button okUpload;

    @FXML
    private Button cancelUpload;

    @FXML
    private Button depositButton;

    @FXML
    private TextField insertWithdrawMoney;

    @FXML
    private Button withdrawButton;

    @FXML
    private Label fundsAvailable;

    @FXML
    private Button UploadItemButton;

    @FXML
    private Label username;

    @FXML
    private Label readHistory1;

    @FXML
    private Label readHistory2;

    @FXML
    private Label readHistory3;

    @FXML
    private Label readHistory4;

    @FXML
    private Label readHistory5;

    @FXML
    private AnchorPane UploadPhotoButton;

    @FXML
    private Button UploadPhoto;

    /**
     * @param event
     */
    @FXML
    private TextField UploadImageDirectory;


    @FXML
    private Button OkUpload;

    @FXML
    private Button CancelUpload;

    @FXML
    void onDepositButtonPressed(ActionEvent event) {
        String money = insertWithdrawMoney.getText();

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

      /*  String data = "aqui";
        ParseClient.getInstance().sendData(data);
        String receiveHead = ParseClient.getInstance().readData();
        ParseClient.getInstance().decodeServerMessage(receiveHead);*/

        FileChooser chooser = new FileChooser();
        System.out.println(chooser);
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selected = chooser.showOpenDialog(Navigation.root.getScene().getWindow());
        Window file = Navigation.root.getScene().getWindow();


        if (selected != null) {
            selected.getAbsolutePath();

            System.out.println("You chose to open this file: " + chooser.getInitialFileName());

            System.out.println("Uploading file: " + selected.getAbsolutePath());
            UploadImageDirectory.setText(selected.getAbsolutePath());
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

        if (Integer.parseInt(money) > Integer.parseInt(fundsAvailable.getText())) {
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

        String path = UploadImageDirectory.getText();
        // ParseClient.getInstance().uploadImage(path);

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
                    default:
                        System.out.println("Cenas by Aires, try again");
                }
            }
        });
    }
}

