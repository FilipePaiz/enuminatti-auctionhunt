package org.academiadecodigo.enuminatti.auctionhunt.server;

import org.academiadecodigo.enuminatti.auctionhunt.client.ParseClient;

import java.io.*;
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


        if (line.startsWith("/regist/")) {
            registerDecodificate(line);
            return;
        }
        if (line.startsWith("/login/")) {
            loginDecodificate(line);
        }
    }


    private void loginDecodificate(String line) {

        line = line.replace("/login/", "");
        String[] words = line.split("#");

        UserService userService = (UserService) ServiceRegistry.getInstance().getService("UserService");
        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

            if (userService.authenticate(words[0], words[1])) {
                out.write("login done");
                return;
            }

            out.write("login not done");
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void registerDecodificate(String line) {

        line = line.replace("/regist/", "");
        String[] words = line.split("#");

        UserService userService = (UserService) ServiceRegistry.getInstance().getService("UserService");

        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

            if (userService.findByName(words[0]) != null) {
                out.write("login done");
                return;
            }

            out.write("login not done");
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


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
