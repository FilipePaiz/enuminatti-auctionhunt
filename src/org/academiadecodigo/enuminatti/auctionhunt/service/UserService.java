package org.academiadecodigo.enuminatti.auctionhunt.service;
import org.academiadecodigo.enuminatti.auctionhunt.model.User;

/**
 * Created by codecadet on 08/11/17.
 */
public interface UserService {

    boolean authenticate(String username, String password);

    void addUser(User user);

    User findByName(String username);

    int count();
}
