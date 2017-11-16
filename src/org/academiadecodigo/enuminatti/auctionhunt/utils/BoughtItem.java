package org.academiadecodigo.enuminatti.auctionhunt.utils;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Someone who is not me on 14/11/17.
 */
public class BoughtItem {

    public static void save(String file, String name, String itemName, String itemID, String price) throws IOException {

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

    private static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
