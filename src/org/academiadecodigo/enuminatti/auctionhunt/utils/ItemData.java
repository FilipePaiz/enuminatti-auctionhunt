package org.academiadecodigo.enuminatti.auctionhunt.utils;

import java.io.*;

/**
 * Created by Someone who is not me on 12/11/17.
 */
public class ItemData {

    public static void save(String file, String name, String url, String price) throws IOException {

        BufferedReader read = new BufferedReader(new FileReader(file));

        String line = "";
        String result = "";

        while ((line = read.readLine()) != null) {
            result += line + "\n";
        }

        BufferedWriter save = new BufferedWriter(new FileWriter(file, true));

        save.write(name + "\n" + url + "\n" + price + "\n<------------------->");
        save.newLine();
        save.flush();
        save.close();
    }
}
