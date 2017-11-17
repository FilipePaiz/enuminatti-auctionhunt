package org.academiadecodigo.enuminatti.auctionhunt.server;

import org.academiadecodigo.enuminatti.auctionhunt.Service;

import java.util.Map;

/**
 * Created by codecadet on 09/11/2017.
 */
public interface BidService extends Service {

    /**
     *
     * @param id
     * @param bidMoney
     * @param user
     */
    //void bidOnItem(int id, int bidMoney, User user);

    /**
     *
     * @param item
     */
    void addItemtoAuction(Item item);

    /**
     *
     * @return
     */
    Map<Integer, Item> getItems();
}
