package org.academiadecodigo.enuminatti.auctionhunt.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.academiadecodigo.enuminatti.auctionhunt.server.ParseServer;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private Label Photo;

    @FXML
    private Button MyFundsButton;

    @FXML
    private Label Money;

    @FXML
    private Label NumberOfItems;

    @FXML
    private Button LogOutButton;

    @FXML
    private Button GoToAuctionButton;

    @FXML
    private Pane DepositWithdrawMoey;

    @FXML
    private Button DepositButton;

    @FXML
    private Button WithdrawButton;

    @FXML
    private TextField InsertWithdrawMoney;

    @FXML
    private Button UploadItemButton;

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

    @FXML
    void onDepositButtonPressed(ActionEvent event) {

    }

    @FXML
    void onGoToAuctionButtonPressed(ActionEvent event) {

        String dataHead = ParseClient.getInstance().setDataServer("item", GoToAuctionButton.getText());
        System.out.println(dataHead);
        ParseClient.getInstance().sendData(dataHead);

        String receiveHead = ParseClient.getInstance().readData();
        System.out.println(receiveHead);
        //String decodeMessage = ParseClient.getInstance().receiveDataServer(receiveHead);

        if (ParseClient.getInstance().decodeServerMessage(receiveHead)) {
            Navigation.getInstance().loadScreen("bidAuction");
            return;
        }

        System.out.println("Something wrong happen");

    }

    @FXML
    void onLogoutButtonPressed(ActionEvent event) {

        Navigation.getInstance().back();
    }

    @FXML
    void onMyFundsButtonPressed(ActionEvent event) {

    }

    @FXML
    void onUploadButtonPressed(ActionEvent event) {

    }

    @FXML
    void onUploadPhotoButtonPressed(ActionEvent event) {

    }

    @FXML
    void onWithdrawButtonPressed(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Money.setText(ParseClient.getInstance().getUserFunds());
        NumberOfItems.setText(ParseClient.getInstance().getUserName());

    }
}

