package org.academiadecodigo.enuminatti.auctionhunt.server;

/**
 * Created by codecadet on 13/11/2017.
 */
public interface MoneyService extends Service {

    boolean withdrawMoney(String name, String money);

    void depositMoney(String name, String money);
}
