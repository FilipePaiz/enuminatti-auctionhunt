package org.academiadecodigo.enuminatti.auctionhunt.server;

/**
 * Created by Someone who is not me on 09/11/17.
 */
public class User {

    private String username;
    private String email;

    public void setFunds(int funds) {
        this.funds = funds;
    }

    private String password;
    private int funds;

    /**
     *
     * @param username
     * @param email
     * @param password
     * @param funds
     */
    public User(String username, String email, String password, int funds) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.funds = funds;
    }

    /**
     *
     * @return
     */
    public int getFunds() {
        return funds;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {

        this.password = password;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

