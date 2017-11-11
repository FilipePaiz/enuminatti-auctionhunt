package org.academiadecodigo.enuminatti.auctionhunt.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ProfileController {

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
    private TextField InsertPathforPhotoUser;

    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private Navigation.LogicController logicController;

    @FXML
    void onDepositButtonPressed(ActionEvent event) {

    }

    @FXML
    void onGoToAuctionButtonPressed(ActionEvent event) {

    }

    @FXML
    void onLogoutButtonPressed(ActionEvent event) {

    }

    @FXML
    void onMyFundsButtonPressed(ActionEvent event) {

    }

    @FXML
    void onUploadButtonPressed(ActionEvent event) {

    }

    @FXML
    void onUploadPhotoButtonPressed(ActionEvent event) {
        uploadPhoto();
    }

    @FXML
    void onWithdrawButtonPressed(ActionEvent event) {

    }

    private void uploadPhoto() {


        byte[] bytes = new byte[1024];


        try {

            Scanner scanner = new Scanner(System.in);
            String path = InsertPathforPhotoUser.getPromptText();

            System.out.println(logicController.getClientSocket());
            dataOutputStream = new DataOutputStream(logicController.getClientSocket().getOutputStream());
            dataInputStream = new DataInputStream(new FileInputStream(path));

            int bytesReader = dataInputStream.read(bytes);

            while (bytesReader != -1) {

                dataOutputStream.write(bytes, 0, bytesReader);
                dataOutputStream.flush();
                bytesReader = dataInputStream.read(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

