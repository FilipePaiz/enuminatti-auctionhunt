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
import org.academiadecodigo.enuminatti.auctionhunt.utils.ItemData;
import org.academiadecodigo.enuminatti.auctionhunt.utils.UserData;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable,Controller {

    @FXML
    private Label Photo;

    @FXML
    private Button MyFundsButton;

    @FXML
    private Label funds;

    @FXML
    private Label numberOfItems;

    @FXML
    private Button logOutButton;

    @FXML
    private Button GoToAuctionButton;

    @FXML
    private Pane depositWithdrawMoey;

    @FXML
    private Button depositButton;

    @FXML
    private Button withdrawButton;

    @FXML
    private TextField insertWithdrawMoney;

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

    /**
     *
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

        transferMoney(money,depositButton.getText());

    }

    /**
     *
     * @param event
     */
    @FXML
    void onGoToAuctionButtonPressed(ActionEvent event) {


        //String decodeMessage = ParseClient.getInstance().receiveDataServer(receiveHead);

            Navigation.getInstance().loadScreen("bidAuction");
            return;



    }

    /**
     *
     * @param event
     */
    @FXML
    void onLogoutButtonPressed(ActionEvent event) {

        try {
            ItemData.save("resources/ItemData", "Aires", "Subaru Imprenza", "resources/Subaru", "995");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Navigation.getInstance().back();
    }

    /**
     *
     * @param event
     */
    @FXML
    void onMyFundsButtonPressed(ActionEvent event) {

    }

    /**
     *
     * @param event
     */
    @FXML
    void onUploadButtonPressed(ActionEvent event) {

      /*  String data = "aqui";
        ParseClient.getInstance().sendData(data);
        String receiveHead = ParseClient.getInstance().readData();
        ParseClient.getInstance().decodeServerMessage(receiveHead);*/


    }

    /**
     *
     * @param event
     */
    @FXML
    void onUploadPhotoButtonPressed(ActionEvent event) {

    }

    /**
     *
     * @param event
     */
    @FXML
    void onWithdrawButtonPressed(ActionEvent event) {

        String money = insertWithdrawMoney.getText();

        if (Integer.parseInt(money) > Integer.parseInt(funds.getText())) {
            return;
        }

        transferMoney(money, withdrawButton.getText());

    }

    private void transferMoney(String money, String buttonText) {

        String moneyAndHead = ParseClient.getInstance().setDataServer(money, buttonText);
        ParseClient.getInstance().sendData(moneyAndHead);
        String serverMessage = ParseClient.getInstance().readData();
        if (!ParseClient.getInstance().decodeServerMessage(serverMessage)) {
            return;
        }
        funds.setText(ParseClient.getInstance().getFunds());

    }

    @FXML
    void onButtonPressedCancelUpload(ActionEvent event) {

    }

    @FXML
    void onButtonPressedOkPressed(ActionEvent event) {

        String path = UploadImageDirectory.getText();
        ParseClient.getInstance().uploadImage(path);

    }

    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(ParseClient.getInstance().getUserName() + " - ProfileController");
        System.out.println(ParseClient.getInstance().getUserFunds() + " - ProfileController");
        showValues();
    }

    private void showValues() {
        funds.setText(ParseClient.getInstance().getUserFunds());
        numberOfItems.setText(ParseClient.getInstance().getUserName());
    }
}

