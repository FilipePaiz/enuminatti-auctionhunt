package org.academiadecodigo.enuminatti.auctionhunt.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.academiadecodigo.enuminatti.auctionhunt.service.MockUserService;
import org.academiadecodigo.enuminatti.auctionhunt.service.UserService;

import java.io.*;
import java.net.Socket;

/**
 * Created by Someone who is not me on 07/11/17.
 */
public class Client extends Application {

    @Override
    public void init() throws Exception {
        UserService userService = new MockUserService();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("org/academiadecodigo/enuminatti/auctionhunt/view/login&register.fxml"));

        if(root == null){
            System.out.println("------------- O MEU ROOT É NULO LOL");
        }

        primaryStage.setTitle("AuctionHunt");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) {

        Application.launch(args);
    }

    public void sendImage(Socket clientSocket) {

        byte[] bytes = new byte[1024];

        try {

            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream("resources/hatchlings_0.jpg"));
            int bytesReaden = dataInputStream.read(bytes);

            while (bytesReaden != -1){
                System.out.println("uahcuhsuhca");
                dataOutputStream.write(bytes, 0, bytesReaden);
                dataOutputStream.flush();
                bytesReaden = dataInputStream.read(bytes);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}