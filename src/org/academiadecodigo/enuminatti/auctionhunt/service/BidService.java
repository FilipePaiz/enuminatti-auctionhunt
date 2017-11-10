package org.academiadecodigo.enuminatti.auctionhunt.service;

import com.sun.javafx.collections.MappingChange;
import org.academiadecodigo.enuminatti.auctionhunt.auxiliary.Item;
import org.academiadecodigo.enuminatti.auctionhunt.model.User;

import java.util.Map;

/**
 * Created by codecadet on 09/11/2017.
 */
public interface BidService extends Service {

    void bidOnItem(int id, int bidMoney, User user);

    void addItemtoAuction(Item item);

    Map<Integer, Item> getItems();
}
