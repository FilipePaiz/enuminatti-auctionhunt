package org.academiadecodigo.enuminatti.auctionhunt.client;

import javafx.application.Application;
import javafx.stage.Stage;
import org.academiadecodigo.enuminatti.auctionhunt.server.Item;
import org.academiadecodigo.enuminatti.auctionhunt.server.ServiceRegistry;
import org.academiadecodigo.enuminatti.auctionhunt.server.User;

import java.io.*;
import java.net.Socket;

/**
 * Created by Someone who is not me on 07/11/17.
 */
public class Client extends Application {

    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    /**
     * @throws Exception
     */
    @Override
    public void init() throws Exception {

    }

    /**
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {


        Navigation.getInstance().setStage(primaryStage);
        Navigation.getInstance().loadScreen("login&register");

       /* Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("org/academiadecodigo/enuminatti/auctionhunt/view/login&register.fxml"));

        primaryStage.setTitle("AuctionHunt");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

*/
        User user = new User("1", "1@gmail.com", "1", 100);
        Item item = new Item(user, "cadeira", "50", 50, "resources/badjoraz.jpg");
        item.bidOnItem(user, 50);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Application.launch(args);
    }

}