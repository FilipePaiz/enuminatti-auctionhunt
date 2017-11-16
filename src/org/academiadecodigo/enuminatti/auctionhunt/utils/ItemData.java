package org.academiadecodigo.enuminatti.auctionhunt.utils;


import org.academiadecodigo.enuminatti.auctionhunt.Server;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Someone who is not me on 12/11/17.
 */

public class ItemData {

    private static int itemNumber = 0;

    private static BufferedWriter save;
    private static BufferedReader read;
    private static ItemData instance;

    private ItemData() {
    }

    /**
     * @return
     */

    /*public static ItemData getInstance() {
        if (instance == null) {
            synchronized (ParseServer.class) {
                if (instance == null) {
                    instance = new ItemData();
                }
            }
        }
        return instance;
    }*/

    /**

     * @param name  receives the name of the owner
     * @param itemName  receives the name of the item
     * @param path  receives the location and the name of the picture
     * @param price receives the price of the item
     * @throws IOException
     */
    public static void save(String name, String itemName, String path, String price) throws IOException {

        String file = "resources/ItemData";
        save = new BufferedWriter(new FileWriter(file, true));

        String newPath = load(file, path);

        save.write("Item ID: " + itemNumber + "\n" +
                "ID: " + name + "\n" +
                "Item name: " + itemName + "\n" +
                "Path: " + newPath + "\n" +
                "Price: " + price + "€\n" +
                "Upload Date: " + getDateTime() +
                "\n<------------------->");

        itemNumber++;

        save.newLine();
        save.flush();
        save.close();
    }

    /**
     *
     * @param file  receives the file to save
     * @param Path  receives the location and the name of the picture
     * @return
     */
    public static String load(String file, String Path) {

        BufferedReader read = null;

        String newPath = Path;

        int counternumber = 1;

        try {
            read = new BufferedReader(new FileReader(file));

            String line = "";

            while ((line = read.readLine()) != null) {
                if (line.contains(Path)) {
                    if(newPath.contains("_" + counternumber)) {
                        counternumber++;
                    }
                    newPath = Path.concat("_" + counternumber);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return newPath;
    }

    /**
     *
     * @return  returns the present day
     */
    private static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String auctionItems(String itemID) {
        try {
            read = new BufferedReader(new FileReader(Server.PATH + "ItemData"));
            String line = read.readLine();
            while (line != null) {
                if (line.equals("Item ID: " + itemID)) {
                    line = read.readLine();
                    while (!line.startsWith("Upload Date: ")) {
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

    public void changeOwner(String username) {

        Path path = FileSystems.getDefault().getPath(Server.PATH, "ItemData");
        try {

            List<String> list = Files.readAllLines(path);
            readListLines(list, username);

            PrintWriter printWriter = new PrintWriter(new FileWriter(Server.PATH + "ItemData"), true);

            for (String newLines : list) {
                printWriter.println(newLines);
            }
            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readListLines(List<String> list, String itemID) {

        int index = 0;
        Iterator<String> iterator = list.iterator();

        LinkedList<String> item = new LinkedList<>();
        int countArray = 0;

        while (iterator.hasNext()) {
            index++;
            String next = iterator.next();
            if (next.equals("Item ID: " + itemID)) {
                next = iterator.next();
                while (!next.startsWith("Upload Date: ")) {
                    countArray++;
                    item.add(next);
                    System.out.println(next);
                    index++;
                    next = iterator.next();
                }

                next = next.replace("Item ID: " + itemID , "");
                list.set(index, next);
                break;
            }
        }
    }

    public  String loadItem(String file, String id) {

        BufferedReader read = null;

        Path path = FileSystems.getDefault().getPath(Server.PATH, "ItemData");
        System.out.println(id);
        try {

            List<String> list = Files.readAllLines(path);

            read = new BufferedReader(new FileReader(file));
            String line = "";

            System.out.println("<---------");
            while ((line = read.readLine()) != null) {
                if (line.contains(id)) {
                     readListLines(list, id);
                    }
                }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }
}