package org.academiadecodigo.enuminatti.auctionhunt.server;

import java.util.HashMap;
import java.util.Map;

public class MockBidService implements BidService {

    private Map<Integer, Item> items;
    private int itemAddedtoAuction;

    /**
     *
     */
    public MockBidService() {

        this.items = new HashMap<>();
    }

    /**
     *
     * @param id
     * @param bidMoney
     * @param user
     */
   /** @Override
    public void bidOnItem(int id, int bidMoney, User user) {

        Item item = items.get(id);

        if (user == null || user == item.getUser()
                || bidMoney <= item.getActualBid()) {
            return;
        }

        item.setActualBid(bidMoney);
        item.setUser(user);
    }*/

    /**
     *
     * @param item
     */
    @Override
    public void addItemtoAuction(Item item) {

        items.put(itemAddedtoAuction,item);
        itemAddedtoAuction++;
    }

    /**
     *
     * @return
     */
    @Override
    public Map<Integer, Item> getItems() {
        return items;
    }
}
