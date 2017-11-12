package org.academiadecodigo.enuminatti.auctionhunt.client;

import org.academiadecodigo.enuminatti.auctionhunt.server.User;

import java.io.*;
import java.net.Socket;

/**
 * Created by codecadet on 10/11/17.
 */
public final class ParseClient implements Runnable {

    private Socket clientSocket = null;
    private static ParseClient instance;
    private String userName;
    private String funds;


    private ParseClient() {
    }

    public static ParseClient getInstance() {
        if (instance == null) {
            synchronized (ParseClient.class) {
                if (instance == null) {
                    instance = new ParseClient();
                }
            }
        }
        return instance;
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

    public String readData() {

        String line = null;

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            line = in.readLine();
            System.out.println(line);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return line;
    }

    // no SendData verificar se é item ou não  -- (profileController  go To AuctionButton)
    // se item €), alterar printWirter para DataOutputStream. e não esquecer o flush... ver do exercicio do webServer.

    public void sendData(String data) {


        try {
            if (data.startsWith("/item/")) {

               int byteReader;

               /// copiar e colar o Bytes [1024]... do webServer.

                while ((byteReader = inFile.read(data))!=-1) {
                    System.out.println("here");

                    webClientOutput.write(fileInBytes,0,byteCounter);

                    System.out.println("Cute image");

                }
                webClientOutput.flush();
                DataOutputStream itemOut = new DataOutputStream(clientSocket.getOutputStream());
                itemOut.write();

                itemOut.flush();
            }

            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
            out.println(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String setDataServer(String data, String buttonId) {

        String[] dataSplitted = data.split(" ");

        switch (buttonId) {

            case "Sign Up": //it is register button, change to a proper name
                return "/regist/" + dataSplitted[0] + "#" + dataSplitted[1] + "#" + dataSplitted[2] + "\r\n";
            case "Sign In":
                return "/login/" + dataSplitted[0] + "#" + dataSplitted[1] + "\r\n";
            case "Go to Auction":
                return "/item/" + dataSplitted[0] + "€" + "aqui tem" + "\r\n";
            default:
                System.out.println("Deu merda o parse do client");

        }
        return null;
    }

    public boolean decodeServerMessage(String string) {

        if(string.equals("login not done")|| string.equals("register not done")){
            return false;
        }

        if(string.startsWith("/login/done/")) {
            string = string.replace("/login/done/", "");
            String[] words = string.split("#");
            userName = words[0];
            funds = words[1];
        }

        return true;
    }


    public String receiveDataServer(String data) {

        String[] dataSplitted = data.split("/");

        switch (dataSplitted[1]) {
            case "login":
                return dataSplitted[2];
            case "regist":
                return dataSplitted[2];
            default:
                System.out.println("Deu merda no parseClient receiveData by Aires CENAS");

        }
        return null;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserFunds() {
        return funds;
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