package org.academiadecodigo.enuminatti.auctionhunt.server;

import org.academiadecodigo.enuminatti.auctionhunt.client.ParseClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by codecadet on 10/11/17.
 */
public final class ParseServer implements Runnable {

    private Socket clientSocket = null;
    private static ParseServer instance;

    private ParseServer() {
    }

    public static ParseServer getInstance() {
        if (instance == null) {
            synchronized (ParseServer.class) {
                if (instance == null) {
                    instance = new ParseServer();
                }
            }
        }
        return instance;
    }


    public void readData() {

        String line = null;

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            line = in.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

        validateData(line);
        //return line;
    }

    private void validateData(String line) {

        

    }


    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        while (true) {

            readData();
        }

    }
}
