package org.academiadecodigo.enuminatti.auctionhunt.server;

import java.io.*;
import java.net.Socket;

/**
 * Created by codecadet on 10/11/17.
 */
public class ParseServer {

    private Socket clientSocket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private UserService userService;

    public ParseServer(Socket clientSocket, BufferedWriter bufferedWriter, BufferedReader bufferedReader) {
        this.clientSocket = clientSocket;
        this.bufferedWriter = bufferedWriter;
        this.bufferedReader = bufferedReader;
        this.userService = (UserService) ServiceRegistry.getInstance().getService("UserService");
    }


    public void decodeMessage() {

       // System.out.println(bufferedReader);
      //  System.out.println(bufferedWriter);

        try {

            String line = bufferedReader.readLine();

            if (line == null) {
                return;
            }

            System.out.println("Received " + line);

            if (line.equals("quit")) {
                System.out.println("Connection terminated");
                clientSocket.close();
                return;
            }

            if (line.startsWith("&&")) {
                registerMessage(line);
                return;
            }

            if(line.startsWith("!!!")){
                loginMessage(line);
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            closeFiles();
        }

    }

    private void loginMessage(String line) {

        line = line.replaceFirst("!!!", "");
        line = line.replaceFirst("@@@", " ");
        String[] userFields = line.split(" ");

        if (!userService.authenticate(userFields[0], userFields[1])) {
            couldNotLogIn();
            return;
        }

        try {
            bufferedWriter.write("LogIn Done");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void couldNotLogIn() {
        try {
            bufferedWriter.write("Couldn't login");
            bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void registerMessage(String line){

        line = line.replaceFirst("&&", "");
        line = line.replaceFirst("##", " ");
        line = line.replaceFirst("!-", " ");
        String[] userFields = line.split(" ");

        if (userService.findByName(userFields[0]) != null) {
            couldNotRegister();
            return;
        }

        userService.addUser(new User(userFields[0], userFields[1], userFields[2]));

        try {
            bufferedWriter.write("Register Done");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void couldNotRegister() {

        try {
            bufferedWriter.write("Couldn't register");
            bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void closeFiles() {

        try {
            bufferedWriter.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
