package org.academiadecodigo.enuminatti.auctionhunt.client;

/**
 * Created by codecadet on 11/11/17.
 */
public class UserClient {
    private String userName;
    private String funds;

    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return
     */
    public String getFunds() {
        return funds;
    }

    /**
     *
     * @param funds
     */
    public void setFunds(String funds) {
        this.funds = funds;
    }
}
