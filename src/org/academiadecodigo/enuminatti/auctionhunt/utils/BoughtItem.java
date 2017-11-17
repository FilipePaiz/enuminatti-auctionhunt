package org.academiadecodigo.enuminatti.auctionhunt.utils;

import org.academiadecodigo.enuminatti.auctionhunt.server.ParseServer;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Someone who is not me on 14/11/17.
 */
public final class BoughtItem {

    private static BoughtItem instance;

    public static BoughtItem getInstance() {
        if (instance == null) {
            synchronized (ParseServer.class) {
                if (instance == null) {
                    instance = new BoughtItem();
                }
            }
        }
        return instance;
    }

    public void save(String file, String name, String itemName, String itemID, String price) throws IOException {

        BufferedReader read = new BufferedReader(new FileReader(file));

        BufferedWriter save = new BufferedWriter(new FileWriter(file, true));

        int itemNumber = 0;

        String line;

        while ((line = read.readLine()) != null) {
            if (line.contains("<------------------->")) {
                itemNumber = itemNumber + 1;
            }

        }

        save.write("ID: " + name + "|\n" +
                "Item: " + itemName + "|\n" +
                "Item ID: " + itemID + "|\n" +
                "Price: " + price + "€|\n" +
                "Date: " + getDateTime() + "|\n<------------------->");

        save.newLine();
        save.flush();
        save.close();
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
