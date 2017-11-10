package org.academiadecodigo.enuminatti.auctionhunt.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.academiadecodigo.enuminatti.auctionhunt.Navigation;
import org.academiadecodigo.enuminatti.auctionhunt.model.Client;
import org.academiadecodigo.enuminatti.auctionhunt.model.Server;
import org.academiadecodigo.enuminatti.auctionhunt.model.User;
import org.academiadecodigo.enuminatti.auctionhunt.service.ServiceRegistry;
import org.academiadecodigo.enuminatti.auctionhunt.service.UserService;
import org.academiadecodigo.enuminatti.auctionhunt.utils.Security;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class LogicController implements Initializable, Controller {

    private UserService userService;

    @FXML
    private Button logOutButton;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private TextField usernameField;

    @FXML
    private Text usernameText;

    @FXML
    private Text passwordText;

    @FXML
    private Text welcomeText;

    @FXML
    private Text auctionText;

    @FXML
    private Hyperlink dontHaveAccount;

    @FXML
    private Text succesfullLog;

    @FXML
    private Text couldNotLogIn;

    @FXML
    private Text emailText;

    @FXML
    private Hyperlink alreadyHaveAccount;

    @FXML
    private Button logInButton;

    @FXML
    private Text succesfullRegister;

    @FXML
    private Text couldNotRegister;

    @FXML
    private TextField emailfield;

    @FXML
    void changeToLogin(ActionEvent event) {
       showLogin();
    }

    @FXML
    void changeToRegister(ActionEvent event) {
        showRegister();
    }

    @FXML
    void onLogin(ActionEvent event) {

        System.out.println("cenas");

        if (usernameField.getText().isEmpty()) {
            couldNotLogIn.setVisible(true);
            return;
        }

        if (passwordfield.getText().isEmpty()) {
            couldNotLogIn.setVisible(true);
            return;
        }

        if (userService.authenticate(usernameField.getText(), passwordfield.getText())) {
            succesfullLog.setVisible(true);
            Navigation.getInstance().loadScreen("Profile");
        } else {
            couldNotLogIn.setVisible(true);
        }

    }

    @FXML
    void onRegister(ActionEvent event) {

        System.out.println(usernameField.getText());
        System.out.println(passwordfield.getText());
        System.out.println(emailfield.getText());

        if (usernameField.getText().isEmpty()) {
            couldNotRegister.setVisible(true);
            return;
        }

        if (passwordfield.getText().isEmpty()) {
            couldNotRegister.setVisible(true);
            return;
        }

        if (emailfield.getText().isEmpty()) {
            couldNotRegister.setVisible(true);
            return;
        }

        if (userService.findByName(usernameField.getText()) != null) {
            couldNotRegister.setVisible(true);
            return;
        }

        System.out.println(userService.count());
        userService.addUser(new User(usernameField.getText(), emailfield.getText(), Security.getHash(passwordfield.getText())));

        System.out.println("bem-vindo");
        succesfullRegister.setVisible(true);
        showLogin();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userService = (UserService) ServiceRegistry.getInstance().getService("UserService");

        Socket clientSocket = null;

        try {

            clientSocket = new Socket(Server.HOST,Server.PORT);
            Client client = new Client();
            client.sendImage(clientSocket);

            showLogin();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showLogin() {
        if(couldNotRegister.isVisible()){
            couldNotRegister.setVisible(false);
        }
        succesfullRegister.setVisible(false);
        emailfield.setVisible(false);
        emailText.setVisible(false);
        logOutButton.setVisible(false);
        alreadyHaveAccount.setVisible(false);
        logInButton.setVisible(true);
        dontHaveAccount.setVisible(true);

    }

    private void showRegister() {
        if(couldNotLogIn.isVisible()){
            couldNotLogIn.setVisible(false);
        }

        emailfield.setVisible(true);
        emailText.setVisible(true);
        logOutButton.setVisible(true);
        alreadyHaveAccount.setVisible(true);
        logInButton.setVisible(false);
        dontHaveAccount.setVisible(false);
    }
}

