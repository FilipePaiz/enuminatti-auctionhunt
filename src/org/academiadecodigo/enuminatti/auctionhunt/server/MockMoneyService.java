package org.academiadecodigo.enuminatti.auctionhunt.server;

import org.academiadecodigo.enuminatti.auctionhunt.utils.UserData;

/**
 * Created by codecadet on 12/11/2017.
 */
public class MockMoneyService implements MoneyService{


    @Override
    public boolean withdrawMoney(String username, String money) {
        int moneyToRemove = Integer.parseInt(money);
        return removeMoney(username,moneyToRemove);
    }

    @Override
    public void depositMoney(String username, String money) {
        int moneyToAdd = Integer.parseInt(UserData.getInstance().userFunds(username)) + Integer.parseInt(money);
        UserData.getInstance().changeUserFunds(username,(moneyToAdd+""));
    }

    /*public static void addMoney(String username, int money) {
        user.setFunds(user.getFunds() + money);
    }*/

    public boolean removeMoney(String username, int money) {

        if(Integer.parseInt(UserData.getInstance().userFunds(username)) < money) {
            System.out.println("Money required not found!");
            return false;
        }
        int newFunds = Integer.parseInt(UserData.getInstance().userFunds(username))-money;
        UserData.getInstance().changeUserFunds(username,(newFunds+""));
        return true;
    }

}
