package org.academiadecodigo.enuminatti.auctionhunt.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.academiadecodigo.enuminatti.auctionhunt.server.Item;
import org.academiadecodigo.enuminatti.auctionhunt.server.BidService;
import org.academiadecodigo.enuminatti.auctionhunt.server.ServiceRegistry;

import javax.naming.ldap.Control;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by codecadet on 08/11/2017.
 */
public class BidController implements Initializable, Controller{

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


    /**
     *
     * @param event
     */
    @FXML
    void OnNextButtonAction(ActionEvent event) {
        //to use this button is needed a linkedlist with items
        itemOnShow++;
        Item item = bidService.getItems().get(itemOnShow);
        showItem(item);
    }

    /**
     *
     * @param event
     */
    @FXML
    void OnPreviousButtonAction(ActionEvent event) {
        //to use this button is needed a linkedlist with items
        itemOnShow--;
        Item item = bidService.getItems().get(itemOnShow);
        showItem(item);
    }

    /**
     *
     * @param event
     */
    @FXML
    void onBidButtonAction(ActionEvent event) {
        //waiting for logic connection
    }

    /**
     *
     * @param event
     */
    @FXML
    void onHomeButtonAction(ActionEvent event) {
        //Awaiting for connection between views
        Navigation.getInstance().back();
    }

    /**
     *
     * @param item
     */
    private void showItem(Item item) {
      /*  lastBid.setText(item.getActualBid()+"");
        askingPrice.setText(item.getAskingPrice()+"");
        itemName.setText(item.getItemName());
        descriptionText.setText(item.getItemDescription());
        itemImage.setImage(new Image(item.getPictureURL()));  */
    }

    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //setText() with values given by item
        bidService = (BidService) ServiceRegistry.getInstance().getService("BidService");
       // showItem(bidService.getItems().get(itemOnShow));
    }
}


