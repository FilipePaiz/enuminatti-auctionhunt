package org.academiadecodigo.enuminatti.auctionhunt.server;

/**
 * Created by Someone who is not me on 07/11/17.
 */
public class Item {

    private User user;
    private String itemName;
    private String itemDescription;
    private int askingPrice; //price setted by user to sell
    private int actualBid; //last bid
    private String pictureURL;

    public Item(User user, String itemName, String itemDescription, int askingPrice, String pictureURL) {
        this.user = user;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.askingPrice = askingPrice;
        this.pictureURL = pictureURL;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public int getAskingPrice() {
        return askingPrice;
    }

    public int getActualBid() {
        return actualBid;
    }

    public void setActualBid(int actualBid) {
        this.actualBid = actualBid;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}