package org.academiadecodigo.enuminatti.auctionhunt.server;

/**
 * Created by codecadet on 08/11/17.
 */
public interface UserService extends Service {

    boolean authenticate(String username, String password);

    void addUser(User user);

    User findByName(String username);

    int count();
}
