package org.academiadecodigo.enuminatti.auctionhunt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.academiadecodigo.enuminatti.auctionhunt.model.Server;

/**
 * Created by Someone who is not me on 07/11/17.
 */
public class Main extends Application {

    private Server server;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("view/login&register.fxml"));
        primaryStage.setTitle("AuctionHunt");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @Override
    public void init() throws Exception {

        server = new Server();
        server.start();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
