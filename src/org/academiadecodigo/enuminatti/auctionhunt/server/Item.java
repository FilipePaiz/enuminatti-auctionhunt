package org.academiadecodigo.enuminatti.auctionhunt.server;

/**
 * Created by Someone who is not me on 07/11/17.
 */
public class Item {

    private String username;
    private String itemName;
    private String itemDescription;
    private int askingPrice;
    private String pictureURL;


    /**
     *
     * @param itemName
     * @param itemDescription

     * @param askingPrice
     */
    public Item(String username, String itemName, String itemDescription, int askingPrice) {
        this.username = username;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.askingPrice = askingPrice;
    }

    /**
     *
     * @return
     */
    public String getItemName() {
        return itemName;
    }

    /**
     *
     * @return
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     *
     * @return
     */
    public int getAskingPrice() {
        return askingPrice;
    }

    /**
     *
     * @return
     */
    public String getPictureURL() {
        return pictureURL;
    }

    /**
     *
     * @return
     */

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setAskingPrice(int askingPrice) {
        this.askingPrice = askingPrice;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

}