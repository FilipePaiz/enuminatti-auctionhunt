package org.academiadecodigo.enuminatti.auctionhunt.client;

import org.academiadecodigo.enuminatti.auctionhunt.server.ParseServer;

import java.io.*;
import java.net.Socket;

/**
 * Created by codecadet on 10/11/17.
 */
public final class HandleClient implements Runnable {

    private Socket clientSocket = null;
    private static HandleClient instance;


    private HandleClient() {
    }

    public static HandleClient getInstance() {
        if (instance == null) {
            synchronized (HandleClient.class) {
                if (instance == null) {
                    instance = new HandleClient();
                }
            }
        }
        return instance;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }


    public String readData() {

        String line = null;

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            line = in.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return line;
    }

    public void sendData(String data) {

        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            out.write(data);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            readData();
        }
    }

    public String setDataServer(String data, String buttonId) {

        String dataToBeSend;
        String[] dataSplitted = data.split(" ");
        switch (buttonId) {
            case "logOutButton": //it is register button, change to a proper name
                return dataToBeSend = "/regist/" + dataSplitted[0] + "#" + dataSplitted[1] + "#" + dataSplitted[2] + "\r\n";
            case "Sign In":
                return dataToBeSend = "/login/" + dataSplitted[0] + "#" + dataSplitted[1] + "\r\n";
            default:
                System.out.println("Deu merda o parse do client");

        }
        return null;
    }

    public String receiveDataServer(String data) {

        String[] dataSplitted = data.split("/");

        switch (dataSplitted[0]) {
            case "login":
                return dataSplitted[1];
            case "regist":
                return dataSplitted[1];
            default:
                System.out.println("Deu merda no parseClient receiveData");

        }
        return null;
    }

    public enum ProtocolMessage {
        LOGIN("login"),
        REGISTER("register");

        private String message;

        ProtocolMessage(String message) {
            this.message = message;
        }


        public String getMessage() {
            return message;
        }
    }


}


