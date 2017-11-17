package org.academiadecodigo.enuminatti.auctionhunt.server;

import org.academiadecodigo.enuminatti.auctionhunt.utils.BoughtItem;
import org.academiadecodigo.enuminatti.auctionhunt.utils.ItemData;
import org.academiadecodigo.enuminatti.auctionhunt.utils.Security;
import org.academiadecodigo.enuminatti.auctionhunt.utils.UserData;

import java.io.*;
import java.net.Socket;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by codecadet on 10/11/17.
 */
public final class ParseServer {

    private Socket clientSocket = null;
    private static ParseServer instance;
    private boolean itemUpload;
    private int itemNumber = 0;

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
            return;
        }
        if (line.startsWith("/deposit/")) {
            depositDecodificate(line);
            return;
        }
        if (line.startsWith("/bid/")) {
            bidDecodificate(line);
            return;
        }
        if (line.startsWith("/next/")) {
            nextDecodificate(line);
        }
        if (line.startsWith("/auction/")) {
            auctionDecodificate(line);
        }


    }

    private void auctionDecodificate(String line) {

        line = line.replace("/auction/", "");
        String[] words = line.split("#");

        System.out.println(Thread.currentThread().getName());
        System.out.println(this);

        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

            String data = ItemData.getInstance().getallItemData(ItemData.FILEPATH, words[0]);


            System.out.println(data + "    aspfkewigjewipgjeipwgji0ewgjeiwpjg");

            String[] dataArray = data.split("#");

            out.println("/auction/done/" + dataArray[0] + "#" + dataArray[1] + "#" + dataArray[3]);

        } catch (
                IOException e)

        {
            e.printStackTrace();
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

        BidService Bidservice = (BidService) ServiceRegistry.getInstance().getService("BidService");

        try {

            String path = "resources/" + lineArray[0] + ".jpg";
            FileOutputStream itemOutput = new FileOutputStream(path);
            DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
            int bytesReaden;

            while (bytesReadTotal != length) {
                bytesReaden = dataIn.read(bytes);
                itemOutput.write(bytes, 0, bytesReaden);
                bytesReadTotal += bytesReaden;
                itemOutput.flush();
            }

            bytesReaden = 0;

            //itemOutput.close();
            System.out.println("done reading");

            ItemData.getInstance().save(lineArray[0], lineArray[1], path, lineArray[3]);
            Bidservice.getItems().put(0, new Item(lineArray[0], lineArray[1], "cenas", Integer.parseInt(lineArray[3])));
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

        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

            String seller = ItemData.getInstance().SearchID(ItemData.FILEPATH, words[1]);

            int sellerUpdateFunds = Integer.parseInt(UserData.getInstance().userFunds(seller)) + Integer.parseInt(words[2]);
            UserData.getInstance().changeUserFunds(seller, (sellerUpdateFunds+""));

            int buyerUpdateFunds = Integer.parseInt(UserData.getInstance().userFunds(words[0])) - Integer.parseInt(words[2]);
            UserData.getInstance().changeUserFunds(words[0], (buyerUpdateFunds+""));

            BoughtItem.getInstance().save("resources/NewOwner", words[0], "Cadeira", words[1], words[2]);

            out.println("/bid/done/" + words[0] + "#" + UserData.getInstance().userFunds(words[0]));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sellItemDecodificate(String line) {

        line = line.replace("/sellItem/", "");
        String[] words = line.split("#");

        BidService bidService = (BidService) ServiceRegistry.getInstance().getService("BidService");

        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void nextDecodificate(String line) {

        line = line.replace("/next/", "");
        String[] words = line.split("#");

        BidService bidService = (BidService) ServiceRegistry.getInstance().getService("BidService");

        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

            Set entrySet = bidService.getItems().entrySet();

            Iterator it = entrySet.iterator();


            if (it.hasNext()) {
                itemNumber++;
                String item = it.next().toString();
                out.println("/next/done/" + words[0] + "#" + item);
                return;
            }

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
