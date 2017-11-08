package org.academiadecodigo.enuminatti.auctionhunt.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by codecadet on 08/11/2017.
 */
public class BidController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bidButton;

    @FXML
    private TextField clientBid;

    @FXML
    private ImageView itemImage;

    @FXML
    private TextArea descriptionText;

    @FXML
    private Label lastBid;

    @FXML
    private Label askingPrice;

    @FXML
    private Label bidResult;

    @FXML
    private Label itemName;

    @FXML
    private Button nextButton;

    @FXML
    private Button previousButton;

    @FXML
    private Button homeButton;

    @FXML
    void OnNextButtonAction(ActionEvent event) {
        //to use this button is needed a linkedlist with items
    }

    @FXML
    void OnPreviousButtonAction(ActionEvent event) {
        //to use this button is needed a linkedlist with items
    }

    @FXML
    void onBidButtonAction(ActionEvent event) {
        //waiting for logic connection
    }

    @FXML
    void onHomeButtonAction(ActionEvent event) {
        //Awaiting for connection between views
    }

    @FXML
    void initialize() {
        //setText() with values given by item
    }
}


