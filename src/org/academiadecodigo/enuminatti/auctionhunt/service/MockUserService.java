package org.academiadecodigo.enuminatti.auctionhunt.service;

import org.academiadecodigo.enuminatti.auctionhunt.model.User;
import org.academiadecodigo.enuminatti.auctionhunt.utils.Security;

import java.util.HashMap;
import java.util.Map;

public class MockUserService implements UserService {

    private Map<String, User> users;

    public MockUserService() {
        users = new HashMap<>();
    }

    @Override
    public boolean authenticate(String username, String password) {
        return users.containsKey(username) &&
                users.get(username).getPassword().equals(Security.getHash(password));
    }

    @Override
    public void addUser(User user) {
        if (!users.containsKey(user.getUsername())) {
            users.put(user.getUsername(), user);
        }
    }

    @Override
    public User findByName(String username) {
        return users.get(username);
    }

    @Override
    public int count() {
        return users.size();
    }

}