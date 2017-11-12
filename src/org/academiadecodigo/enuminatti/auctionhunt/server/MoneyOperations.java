package org.academiadecodigo.enuminatti.auctionhunt.server;

/**
 * Created by codecadet on 12/11/2017.
 */
public class MoneyOperations {

    public static void addMoney(User user, int money) {
        user.setFunds(user.getFunds() + money);
    }

    public static boolean removeMoney (User user, int money) {
        if(user.getFunds() < money) {
            System.out.println("Money required not found!");
            return false;
        }
        user.setFunds(user.getFunds()-money);
        return true;
    }
}
