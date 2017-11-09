package org.academiadecodigo.enuminatti.auctionhunt.service;

import org.academiadecodigo.enuminatti.auctionhunt.auxiliary.Item;
import org.academiadecodigo.enuminatti.auctionhunt.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by codecadet on 09/11/2017.
 */
public class MockBidService implements BidService {

    private HashMap<Integer, Item> items;
    private int itemAddedtoAuction;

    public MockBidService(HashMap<Integer, Item> items) {

        this.items = items;
    }

    @Override
    public void bidOnItem(int id, int bidMoney, User user) {

        Item item = items.get(id);

        if (user == null || user == item.getUser()
                || bidMoney <= item.getActualBid()) {
            return;
        }

        item.setActualBid(bidMoney);
        item.setUser(user);
    }

    @Override
    public void addItemtoAuction(Item item) {

        items.put(itemAddedtoAuction,item);
        itemAddedtoAuction++;
    }

    @Override
    public Map<Integer, Item> getItems() {
        return items;
    }
}
