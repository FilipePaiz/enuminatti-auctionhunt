package org.academiadecodigo.enuminatti.auctionhunt.server;

import org.academiadecodigo.enuminatti.auctionhunt.utils.ItemData;
import org.academiadecodigo.enuminatti.auctionhunt.utils.Security;
import org.academiadecodigo.enuminatti.auctionhunt.utils.UserData;

import java.io.*;
import java.net.Socket;

/**
 * Created by codecadet on 10/11/17.
 */
public final class ParseServer {

    private Socket clientSocket = null;
    private static ParseServer instance;
    private boolean itemUpload;

    /**
     *
     */
    private ParseServer() {
    }

    /**
     * @return
     */
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


    /**
     * @param line
     */
    public void validateData(String line) {

        if (line.startsWith("/regist/")) {
            registerDecodificate(line);
            return;
        }
        if (line.startsWith("/login/")) {
            loginDecodificate(line);
            return;
        }
        if (line.startsWith("/item/")) {
            itemDecodificate(line);
            return;
        }

        if (line.startsWith("/withdraw/")) {
            withdrawDecodificate(line);
        }
        if (line.startsWith("/deposit/")) {
            depositDecodificate(line);
        }
        if (line.startsWith("/bid/")) {
            bidDecodificate(line);
        }

    }

    private void depositDecodificate(String line) {

        line = line.replace("/deposit/", "");
        String[] words = line.split("#");

        System.out.println(Thread.currentThread().getName());
        System.out.println(this);

        MoneyService moneyService = (MoneyService) ServiceRegistry.getInstance().getService("MoneyService");

        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

            moneyService.depositMoney(words[0], words[1]);

            out.println("/deposit/done/" + words[0] + "#" + UserData.getInstance().userFunds(words[0]));

        } catch (
                IOException e)

        {
            e.printStackTrace();
        }

    }


    private void withdrawDecodificate(String line) {

        line = line.replace("/withdraw/", "");
        String[] words = line.split("#");

        MoneyService moneyService = (MoneyService) ServiceRegistry.getInstance().getService("MoneyService");

        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

            if (!moneyService.withdrawMoney(words[0], words[1])) {
                return;
            }

            out.println("/withdraw/done/" + words[0] + "#" + UserData.getInstance().userFunds(words[0]));

        } catch (
                IOException e)

        {
            e.printStackTrace();
        }

    }

    /**
     * @param line
     */
    private void itemDecodificate(String line) {

        System.out.println("LINE SERVER:" + line);
        byte[] bytes = new byte[1024];
        line = line.replace("/item/", "");

        String[] lineArray = line.split("#");

        int length = Integer.parseInt(lineArray[4]);
        System.out.println(length);

        int bytesReadTotal = 0;

                System.out.println(line + "<-----------");
        try {

            String path = "resources/" +  lineArray[0] + ".jpg";
            FileOutputStream itemOutput = new FileOutputStream(path);
            DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
            int bytesReaden;

            while (bytesReadTotal != length) {
                System.out.println("filipe");
                bytesReaden = dataIn.read(bytes);
                itemOutput.write(bytes, 0, bytesReaden);
                bytesReadTotal += bytesReaden;
                itemOutput.flush();
            }

            bytesReadTotal = 0;

            //itemOutput.close();
            System.out.println("done reading");

            ItemData.save(lineArray[0],lineArray[1],path,lineArray[3]);
            System.out.println("Item save ");

            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
            out.println("/item/done/");
            System.out.println("message sent");

        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(words[0]);
    }


    /**
     * @param line
     */
    private void loginDecodificate(String line) {


        line = line.replace("/login/", "");
        String[] words = line.split("#");

        UserService userService = (UserService) ServiceRegistry.getInstance().getService("UserService");
        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

            if (userService.authenticate(words[0], words[1])) {
                out.println("/login/done/" + words[0] + "#" + userService.getUserFunds(words[0]));
                return;
            }

            out.println("login not done");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param line
     */
    private void registerDecodificate(String line) {

        line = line.replace("/regist/", "");
        String[] words = line.split("#");

        UserService userService = (UserService) ServiceRegistry.getInstance().getService("UserService");

        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

            if (!userService.findByName(words[0])) {
                userService.addUser(new User(words[0], words[1], Security.getHash(words[2]), 0));
                System.out.println("Users: " + userService.count());
                out.println("/regist/done/" + line);
                return;

            }

            out.println("register not done");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private void bidDecodificate(String line) {

        line = line.replace("/bid/", "");
        String[] words = line.split("#");

        MoneyService moneyService = (MoneyService) ServiceRegistry.getInstance().getService("MoneyService");

        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

            int money = Integer.parseInt(words[1]);

            if(!moneyService.removeMoney(words[0], money)){
                return;
            }

            out.println("/bid/done/" + words[0] + "#" + UserData.getInstance().userFunds(words[0]));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * @param clientSocket
     */
    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

}
