package org.academiadecodigo.enuminatti.auctionhunt.service;

import org.academiadecodigo.enuminatti.auctionhunt.model.Client;
import org.academiadecodigo.enuminatti.auctionhunt.utils.Security;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by codecadet on 08/11/17.
 */
public class MockUserService implements UserService {

    private Map<String, Client> clients;

    public MockUserService() {
        clients = new HashMap<>();
    }

    public MockUserService(Map<String, Client> clients) {
        this.clients = clients;
    }

    @Override
    public boolean authenticate(String username, String password) {
        return clients.containsKey(username) &&
                clients.get(username).getPassword().equals(Security.getHash(password));
    }

    @Override
    public void addUser(Client client) {
        if (!clients.containsKey(client.getUsername())) {
            clients.put(client.getUsername(), client);
        }
    }

    @Override
    public Client findByName(String username) {
        return clients.get(username);
    }

    @Override
    public int count() {
        return clients.size();
    }
}
