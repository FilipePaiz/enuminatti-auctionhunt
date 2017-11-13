package org.academiadecodigo.enuminatti.auctionhunt.server;

import org.academiadecodigo.enuminatti.auctionhunt.utils.Security;
import org.academiadecodigo.enuminatti.auctionhunt.utils.UserData;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MockUserService implements UserService {

    private Map<String, User> users;

    /**
     *
     */
    public MockUserService() {
        users = new HashMap<>();
    }

    /**
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean authenticate(String username, String password) {
        return UserData.getInstance().authenticate(username, password);
    }

    /**
     * @param user
     */
    @Override
    public void addUser(User user) {

        if (!UserData.getInstance().getUser(user)) {
            UserData.getInstance().addUser(user);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String getUserFunds(String username){
        return UserData.getInstance().userFunds(username);
    }

    /**
     * @param username
     * @return
     */
    @Override
    public boolean findByName(String username) {
        return UserData.getInstance().existUser(username);
    }

    /**
     * @return
     */
    @Override
    public int count() {
        return UserData.getInstance().size();
    }

}