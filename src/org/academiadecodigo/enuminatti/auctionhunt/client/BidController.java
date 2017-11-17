package org.academiadecodigo.enuminatti.auctionhunt.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.academiadecodigo.enuminatti.auctionhunt.server.Item;
import org.academiadecodigo.enuminatti.auctionhunt.server.ParseServer;
import org.academiadecodigo.enuminatti.auctionhunt.server.ServiceRegistry;
import org.academiadecodigo.enuminatti.auctionhunt.utils.ItemData;

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


        String next = nextButton.getText();
        String data = ParseClient.getInstance().setDataServer(next, nextButton.getText());
        communicationService.sendData(data);
    }

    /**
     * @param event
     */
    @FXML
    void OnPreviousButtonAction(ActionEvent event) {
        //ItemData.getInstance().loadItem("resources/ItemData","2");
    }

    /**
     * @param event
     */
    @FXML
    void onBidButtonAction(ActionEvent event) {


        int funds = Integer.parseInt(ParseClient.getInstance().getFunds());

        int price = Integer.parseInt(ParseClient.getInstance().getItemPrice());

        if (funds < price) {
            return;
        }

        String moneyData = ParseClient.getInstance().getItemPrice();
        String data = ParseClient.getInstance().setDataServer(moneyData, bidButton.getText());
        communicationService.sendData(data);

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


    private void showItem() {

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

    public void changeView(String string) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                switch (string) {

                    case "auction":
                        askingPrice.setText(ParseClient.getInstance().getItemPrice());
                        itemName.setText(ParseClient.getInstance().getItemName());
                        break;
                    case "bid":
                        itemName.setText("SOLD");
                        break;
                    default:
                        System.out.println("Cenas by Aires, try again");
                }
            }
        });
    }


}


