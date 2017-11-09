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
    private String username;
    private String password;
    private String email;


    public Client(Socket clientSocket, String username, String email, String password ) {
        this.clientSocket = clientSocket;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public static void main(String[] args) {

        Socket clientSocket = null;

        try {


            clientSocket = new Socket(Server.HOST,Server.PORT);
            Client client = new Client(clientSocket);
            new Thread(client).start();
            client.sendImage();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendImage() {

        DataInputStream dataInputStream;
        DataOutputStream dataOutputStream;
        byte[] bytes = new byte[1024];

        try {

            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            dataInputStream = new DataInputStream(new FileInputStream(Server.PATH + path +".jpg"));
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

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public void addMoney(String moneyPlus){
        int moneyAdded = Integer.parseInt(moneyPlus);
        this.money = money + moneyAdded;
    }

    public int getMoney() {
        return money;
    }
}
