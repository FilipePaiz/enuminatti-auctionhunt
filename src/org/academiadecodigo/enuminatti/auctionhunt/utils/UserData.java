package org.academiadecodigo.enuminatti.auctionhunt.utils;

import org.academiadecodigo.enuminatti.auctionhunt.server.ParseServer;
import org.academiadecodigo.enuminatti.auctionhunt.server.Server;
import org.academiadecodigo.enuminatti.auctionhunt.server.User;

import java.io.*;

/**
 * Created by codecadet on 12/11/17.
 */
public final class UserData {

    private int count;
    private static UserData instance;
    private BufferedWriter save;
    private BufferedReader read;

    private UserData() {
    }

    /**
     *
     * @return
     */
    public static UserData getInstance() {
        if (instance == null) {
            synchronized (ParseServer.class) {
                if (instance == null) {
                    instance = new UserData();
                }
            }
        }
        return instance;
    }

    public void addUser(User user) {

        try {

            save = new BufferedWriter(new FileWriter(Server.PATH + "UserData", true));
            save.write(user.toString());
            save.newLine();
            save.write("<------------------->");
            save.newLine();
            save.flush();

            save.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    public boolean getUser(User user) {

        try {

            read = new BufferedReader(new FileReader(Server.PATH + "UserData"));
            String line = read.readLine();
            while (line != null) {
                if (line.equals("Username: " + user.getUsername())) {
                    return true;
                }
                line = read.readLine();
            }
            read.close();
            return false;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int size() {
        return count;
    }

    public boolean authenticate(String username, String password) {

        try {
            read = new BufferedReader(new FileReader(Server.PATH + "UserData"));
            String line = read.readLine();
            while (line != null) {
                if (line.equals("Username: " + username) && (read.readLine()).equals("Password: " + Security.getHash(password))) {
                    return true;
                }
                line = read.readLine();
            }
            read.close();
            return false;
        } catch (IOException e) {
            e.printStackTrace();

        }

        return false;
    }

    public boolean existUser(String username) {

        try {
            read = new BufferedReader(new FileReader(Server.PATH + "UserData"));
            String line = read.readLine();
            while (line != null) {
                if (line.equals("Username: " + username)) {
                    return true;
                }
                line = read.readLine();
            }
            read.close();
            return false;
        } catch (IOException e) {
            e.printStackTrace();

        }
        return false;
    }

    public String userFunds(String username) {

        try {
            read = new BufferedReader(new FileReader(Server.PATH + "UserData"));
            String line = read.readLine();
            while (line != null) {
                if (line.equals("Username: " + username)) {
                    line = read.readLine();
                    while (!line.startsWith("Funds: ")) {
                        line = read.readLine();
                    }
                    String[] words = line.split(" ");
                    return words[1];
                }
                line = read.readLine();
            }
            read.close();
            return null;

        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }
}
