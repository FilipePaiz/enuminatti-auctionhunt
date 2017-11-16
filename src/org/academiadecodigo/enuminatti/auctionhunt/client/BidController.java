package org.academiadecodigo.enuminatti.auctionhunt.client;

import javafx.application.Platform;
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
import org.academiadecodigo.enuminatti.auctionhunt.server.ParseServer;
import org.academiadecodigo.enuminatti.auctionhunt.server.ServiceRegistry;
import org.academiadecodigo.enuminatti.auctionhunt.utils.BoughtItem;
import org.academiadecodigo.enuminatti.auctionhunt.utils.ItemData;

import javax.naming.ldap.Control;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by codecadet on 08/11/2017.
 */
public class BidController implements Initializable, Controller {

    private CommunicationService communicationService;

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
     * @param event
     */
    @FXML
    void OnNextButtonAction(ActionEvent event) {
        try {
            ItemData.save("resources/ItemData", "Aires", "Subaru Imprenza", "resources/Subaru", "995");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param event
     */
    @FXML
    void OnPreviousButtonAction(ActionEvent event) {

    }

    /**
     * @param event
     */
    @FXML
    void onBidButtonAction(ActionEvent event) {

       if (clientBid.getText().equals("")){
           clientBid.setText("0");
       }

        String moneyData = clientBid.getText();
        String data = ParseClient.getInstance().setDataServer(moneyData, bidButton.getText());
        communicationService.sendData(data);

        ParseClient.getInstance().decodeServerMessage("bid");

        clientBid.clear();

       /** try {
            BoughtItem.save(Server.PATH + "NewOwner", ParseClient.getInstance().getUserName(), "SUBARU", "#2", clientBid.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    /**
     * @param event
     */
    @FXML
    void onHomeButtonAction(ActionEvent event) {
        Navigation.getInstance().back();
        Navigation.getInstance().back();
        Navigation.getInstance().loadScreen("Profile");
    }

    /**
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
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //setText() with values given by item
        communicationService = (CommunicationService) ServiceRegistry.getInstance().getService("CommunicationService");
        // showItem(bidService.getItems().get(itemOnShow));
    }
}


