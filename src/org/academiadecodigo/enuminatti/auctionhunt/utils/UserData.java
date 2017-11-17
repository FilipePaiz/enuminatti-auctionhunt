package org.academiadecodigo.enuminatti.auctionhunt.utils;

import org.academiadecodigo.enuminatti.auctionhunt.Server;
import org.academiadecodigo.enuminatti.auctionhunt.server.ParseServer;
import org.academiadecodigo.enuminatti.auctionhunt.server.User;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;

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

            //  save.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    public boolean getUser(User user) {

        try {

            read = new BufferedReader(new FileReader(Server.PATH + "UserData"));
            String line = read.readLine();
            while (line != null) {
                if (line.equals("Username= " + user.getUsername())) {
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
                if (line.equals("Username= " + username) && (read.readLine()).equals("Password= " + Security.getHash(password))) {
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
                if (line.equals("Username= " + username)) {
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

        Path path = FileSystems.getDefault().getPath(Server.PATH, "UserData");
        try {

            List<String> list = Files.readAllLines(path);
            synchronized (list) {
                return findUser(list, username);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String findUser(List<String> list, String username) {

        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.equals("Username= " + username)) {
                next = iterator.next();
                while (!next.startsWith("Funds= ")) {
                    next = iterator.next();
                }

                String[] array = next.split(" ");
                return array[1];
            }
        }

        return null;
    }

    public void changeUserFunds(String username, String funds) {

        Path path = FileSystems.getDefault().getPath(Server.PATH, "UserData");
        try {

            List<String> list = Files.readAllLines(path);

            synchronized (list) {
                System.out.println(list.size());
                readListLines(list, username, funds);

                PrintWriter printWriter = new PrintWriter(new FileWriter(Server.PATH + "UserData"), true);

                for (String newLines : list) {
                    printWriter.println(newLines);
                }
                printWriter.close();

                list = Files.readAllLines(path);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readListLines(List<String> list, String username, String funds) {

        int index = 0;
        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            index++;
            String next = iterator.next();
            if (next.equals("Username= " + username)) {
                next = iterator.next();
                while (!next.startsWith("Funds= ")) {
                    index++;
                    next = iterator.next();
                }

                next = next.replace("Funds= " + userFunds(username), "Funds= " + funds);
                list.set(index, next);
                break;
            }
        }
    }

}
