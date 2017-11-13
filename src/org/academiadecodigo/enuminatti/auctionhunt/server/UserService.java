package org.academiadecodigo.enuminatti.auctionhunt.server;

/**
 * Created by codecadet on 08/11/17.
 */
public interface UserService extends Service {

    /**
     *
     * @param username
     * @param password
     * @return
     */
    boolean authenticate(String username, String password);

    /**
     *
     * @param user
     */
    void addUser(User user);

    /**
     *
     * @param username
     * @return
     */
    boolean findByName(String username);

    /**
     *
     * @return
     */
    int count();

    String getUserFunds(String username);
}
