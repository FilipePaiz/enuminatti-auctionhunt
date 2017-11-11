package org.academiadecodigo.enuminatti.auctionhunt.client;

import java.io.*;
import java.net.Socket;

/**
 * Created by codecadet on 10/11/17.
 */
public class ParseClient {

    private Socket clientSocket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public ParseClient(Socket clientSocket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        this.clientSocket = clientSocket;
        this.bufferedReader = bufferedReader;
        this.bufferedWriter = bufferedWriter;
    }

    public void createRegisterMessage(String username, String emailfieldText, String passwordfieldText) {

        try {
            bufferedWriter.write("&&" + username + "##" + emailfieldText + "!-" + passwordfieldText);
            bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createLogInMessage(String username, String password) {

        try {
            bufferedWriter.write("!!!" + username + "@@@" + password);
            bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean receiveMessage(String line) {

        if (line.equals("Couldn't Register") || line.equals("Couldn't login")) {
            return false;
        }
        if(line.equals("Register Done") || line.equals("LogIn Done")) {
            return true;
        }
        return false;
    }

}
