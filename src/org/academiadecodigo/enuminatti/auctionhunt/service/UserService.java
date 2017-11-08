package org.academiadecodigo.enuminatti.auctionhunt.service;

import org.academiadecodigo.enuminatti.auctionhunt.model.Client;

/**
 * Created by codecadet on 08/11/17.
 */
public interface UserService {

    boolean authenticate(String username, String password);

    void addUser(Client user);

    Client findByName(String username);

    int count();
}
