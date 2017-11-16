package org.academiadecodigo.enuminatti.auctionhunt;

import javafx.application.Application;
import javafx.stage.Stage;
import org.academiadecodigo.enuminatti.auctionhunt.client.CommunicationService;
import org.academiadecodigo.enuminatti.auctionhunt.client.Navigation;
import org.academiadecodigo.enuminatti.auctionhunt.server.ServiceRegistry;

import java.io.*;
import java.net.Socket;

/**
 * Created by Someone who is not me on 07/11/17.
 */
public class Client extends Application {

    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private CommunicationService communicationService;

    /**
     *
     * @throws Exception
     */
    @Override
    public void init() throws Exception {

        try {

            Socket clientSocket = new Socket(Server.HOST, Server.PORT);
            communicationService = new CommunicationService();
            //ParseClient.getInstance().setClientSocket(clientSocket);
            communicationService.setClientSocket(clientSocket);


            ServiceRegistry.getInstance().addService("CommunicationService", communicationService);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {


        Navigation.getInstance().setStage(primaryStage);
        Navigation.getInstance().loadScreen("login&register");
        new Thread(communicationService).start();

       /* Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("org/academiadecodigo/enuminatti/auctionhunt/view/login&register.fxml"));

        primaryStage.setTitle("AuctionHunt");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
*/
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {


        Application.launch(args);
    }

}