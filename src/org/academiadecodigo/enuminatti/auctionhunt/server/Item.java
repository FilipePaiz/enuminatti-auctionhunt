package org.academiadecodigo.enuminatti.auctionhunt.server;

/**
 * Created by Someone who is not me on 07/11/17.
 */
public class Item {

    private User user;
    private String itemName;
    private int id;
    private String itemDescription;
    private int askingPrice; //price setted by user to sell
    private int actualBid; //last bid
    private String pictureURL;

    /**
     *
     * @param user
     * @param itemName
     * @param itemDescription
     * @param askingPrice
     * @param pictureURL
     */
    public Item(User user, String itemName, String itemDescription, int askingPrice, String pictureURL) {
        this.user = user;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.askingPrice = askingPrice;
        this.actualBid = askingPrice;
        this.pictureURL = pictureURL;
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
    public int getActualBid() {
        return actualBid;
    }

    /**
     *
     * @param actualBid
     */
    public void setActualBid(int actualBid) {
        this.actualBid = actualBid;
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
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @param newBidder
     * @param money
     * @return
     */
    public boolean bidOnItem(User newBidder, int money) {
        if(!MoneyOperations.removeMoney(newBidder,money) || money < actualBid) {
            System.out.println("Bid not made"); //this will remove the money from bid to the actual bidder
            return false;
        }

        MoneyOperations.addMoney(user, actualBid); //this will return the money to the previous bidder
        user = newBidder;
        actualBid = money;
        return true;
    }
}