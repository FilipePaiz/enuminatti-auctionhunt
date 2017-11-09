package org.academiadecodigo.enuminatti.auctionhunt.service;

import org.academiadecodigo.enuminatti.auctionhunt.auxiliary.Item;

/**
 * Created by codecadet on 09/11/2017.
 */
public interface BidService extends Service {

    void bidItem(int bidMoney);

    void addItemtoAuction(Item item);
}
