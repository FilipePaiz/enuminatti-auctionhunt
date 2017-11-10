package org.academiadecodigo.enuminatti.auctionhunt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.academiadecodigo.enuminatti.auctionhunt.service.UserService;

/**
 * Created by Someone who is not me on 07/11/17.
 */
public class Main extends Application {

    private UserService userService;

    @Override
    public void init() throws Exception {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/login&register.fxml"));
        primaryStage.setTitle("AuctionHunt");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}