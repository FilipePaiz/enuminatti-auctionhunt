package org.academiadecodigo.enuminatti.auctionhunt.utils;

import java.io.*;

/**
 * Created by Someone who is not me on 12/11/17.
 */
public class ItemData {

    public static void save(String file, String name,  String itemName, String url, String price) throws IOException {

        BufferedWriter save = new BufferedWriter(new FileWriter(file, true));

        save.write("ID: " + name + "\n" +
                "Item name: " + itemName + "\n" +
                "Path: " + url + "\n" +
                "Price: " + price + "€\n<------------------->");
        save.newLine();
        save.flush();
        save.close();
    }
}
