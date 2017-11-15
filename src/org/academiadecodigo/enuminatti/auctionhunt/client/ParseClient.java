package org.academiadecodigo.enuminatti.auctionhunt.client;

import java.io.*;
import java.net.Socket;

/**
 * Created by codecadet on 10/11/17.
 */
public final class ParseClient {

    private Socket clientSocket = null;
    private static ParseClient instance;
    private String userName;
    private String funds;

    /**
     *
     */
    private ParseClient() {
    }

    public String getFunds() {
        return funds;
    }

    public void setFunds(String funds) {
        this.funds = funds;
    }

    /**
     * @return
     */
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

    /**
     * @param clientSocket
     */
    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     *
     */


    /**
     * @return
     */

    /**
     * @param data
     * @param buttonId
     * @return
     */
    public String setDataServer(String data, String buttonId) {

        String[] dataSplitted = data.split(" ");

        switch (buttonId) {

            case "Sign Up": //it is register button, change to a proper name
                return "/regist/" + dataSplitted[0] + "#" + dataSplitted[1] + "#" + dataSplitted[2] + "\r\n";
            case "Sign In":
                return "/login/" + dataSplitted[0] + "#" + dataSplitted[1] + "\r\n";
            case "Go to Auction":
                return "/item/" + dataSplitted[0] + "#" + "aqui tem" + "\r\n";
            case "Withdraw":
                return "/withdraw/" + userName + "#" + dataSplitted[0] + "\r\n";
            case "Deposit":
                return "/deposit/" + userName + "#" + dataSplitted[0] + "\r\n";
            /*case "next"
                return "/item/" + dataSplitted[0] + "€" + "aqui tem" + "\r\n";*/
            default:
                System.out.println("Deu merda o parse do client");

        }
        return null;
    }

    /**
     * @param string
     * @return
     */
    public String decodeServerMessage(String string) {

        if (string.equals("login not done") || string.equals("register not done")) {
            return null;
        }

        if (string.startsWith("/login/done/")) {
            string = string.replace("/login/done/", "");
            String[] words = string.split("#");
            userName = words[0];
            funds = words[1];
            System.out.println(userName);
            System.out.println(funds);
            return "login";
        }

        if (string.startsWith("/withdraw/done/")) {
            string = string.replace("/withdraw/done/", "");
            String[] words = string.split("#");
            if (!userName.equals(words[0])) {
                return null;
            }
            funds = words[1];
            return "withdraw";
        }

        if (string.startsWith("/deposit/done/")) {
            string = string.replace("/deposit/done/", "");
            String[] words = string.split("#");
            if (!userName.equals(words[0])) {
                return null;
            }
            funds = words[1];
            return "deposit";
        }

        return "register";
    }

    /**
     * fgrhtjhgr
     *
     * @param data Data to be received by the server
     * @return lalal
     */
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

    /**
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return
     */
    public String getUserFunds() {
        return funds;
    }

    /**
     *
     */
    public enum ProtocolMessage {
        LOGIN("login"),
        REGISTER("register");


        private String message;

        /**
         * @param message
         */
        ProtocolMessage(String message) {
            this.message = message;
        }

        /**
         * @return
         */
        public String getMessage() {
            return message;
        }


    }




}