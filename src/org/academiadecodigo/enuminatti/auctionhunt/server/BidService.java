package org.academiadecodigo.enuminatti.auctionhunt.server;

import java.util.Map;

/**
 * Created by codecadet on 09/11/2017.
 */
public interface BidService extends Service {

    void bidOnItem(int id, int bidMoney, User user);

    void addItemtoAuction(Item item);

    Map<Integer, Item> getItems();
}
