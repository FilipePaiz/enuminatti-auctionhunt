package org.academiadecodigo.enuminatti.auctionhunt.model;

import org.academiadecodigo.enuminatti.auctionhunt.auxiliary.Money;

import java.io.*;
import java.net.Socket;

/**
 * Created by Someone who is not me on 07/11/17.
 */
public class Client implements Runnable{

    private int money;
    private Socket clientSocket;
    private boolean connectionStatus;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;


    public Client(int money, Socket clientSocket) {
        this.money = money;
        this.clientSocket = clientSocket;
    }

    public static void main(String[] args) {

        Socket clientSocket = null;

        try {

            clientSocket = new Socket(Server.HOST,Server.PORT);
            Client client = new Client(400, clientSocket);
            new Thread(client).start();
            client.sendImage();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendImage() {

        byte[] bytes = new byte[1024];

        try {

            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            dataInputStream = new DataInputStream(new FileInputStream("resources/hatchlings_0.jpg"));
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


    public boolean isConnectionStatus() {
        return connectionStatus;
    }

    /**
     * Receive image from the server
     */

    @Override
    public void run() {


        //controller e todos os métodos da vida do Client!

    }
}
