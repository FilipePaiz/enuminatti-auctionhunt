package org.academiadecodigo.enuminatti.auctionhunt.server;

import org.academiadecodigo.enuminatti.auctionhunt.utils.Security;
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
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean authenticate(String username, String password) {
        return users.containsKey(username) &&
                users.get(username).getPassword().equals(Security.getHash(password));
    }

    /**
     *
     * @param user
     */
    @Override
    public void addUser(User user) {
        if (!users.containsKey(user.getUsername())) {
            users.put(user.getUsername(), user);
        }
    }

    /**
     *
     * @param username
     * @return
     */
    @Override
    public User findByName(String username) {
        return users.get(username);
    }

    /**
     *
     * @return
     */
    @Override
    public int count() {
        return users.size();
    }

}