package org.academiadecodigo.enuminatti.auctionhunt.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.academiadecodigo.enuminatti.auctionhunt.auxiliary.Item;
import org.academiadecodigo.enuminatti.auctionhunt.service.BidService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by codecadet on 08/11/2017.
 */
public class BidController {

    private BidService bidService;
    private int itemOnShow;

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
        itemOnShow++;
        Item item = bidService.getItems().get(itemOnShow);
        showItem(item);
    }

    @FXML
    void OnPreviousButtonAction(ActionEvent event) {
        //to use this button is needed a linkedlist with items
        itemOnShow--;
        Item item = bidService.getItems().get(itemOnShow);
        showItem(item);
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
        showItem(bidService.getItems().get(itemOnShow));
    }

    private void showItem(Item item) {
        lastBid.setText(item.getActualBid()+"");
        askingPrice.setText(item.getAskingPrice()+"");
        itemName.setText(item.getItemName());
        descriptionText.setText(item.getItemDescription());
        itemImage.setImage(new Image(item.getPictureURL()));
    }


}


