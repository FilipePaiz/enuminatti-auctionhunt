package org.academiadecodigo.enuminatti.auctionhunt.client;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

/**
 * Created by Someone who is not me on 07/11/17.
 */
public class Client extends Application {

    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    @Override
    public void init() throws Exception {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Navigation.getInstance().setStage(primaryStage);
        Navigation.getInstance().loadScreen("login&register");

       /* Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("org/academiadecodigo/enuminatti/auctionhunt/view/login&register.fxml"));

        primaryStage.setTitle("AuctionHunt");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
*/
    }

    public static void main(String[] args) {

        Application.launch(args);
    }

}