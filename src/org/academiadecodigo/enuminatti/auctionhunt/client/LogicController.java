package org.academiadecodigo.enuminatti.auctionhunt.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.academiadecodigo.enuminatti.auctionhunt.server.Server;

import javax.naming.ldap.Control;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class LogicController implements Initializable, Controller {

    private Socket clientSocket;

    @FXML
    private Button logOutButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField hostField;

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
    private TextField emailField;

    @FXML
    void changeToLogin(ActionEvent event) {
       showLogin();
    }

    @FXML
    void changeToRegister(ActionEvent event) {
        showRegister();
    }

    /**
     *
     * @param event
     */
    @FXML
    void onLogin(ActionEvent event) {

        if(clientSocket == null) {

            try {

                clientSocket = new Socket(hostField.getText(), Server.PORT);
                ParseClient.getInstance().setClientSocket(clientSocket);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (usernameField.getText().isEmpty()) {
            couldNotLogIn.setVisible(true);
            return;
        }

        if (passwordField.getText().isEmpty()) {
            couldNotLogIn.setVisible(true);
            return;
        }

        String data = usernameField.getText() + " " + passwordField.getText();

        String dataAndHead = ParseClient.getInstance().setDataServer(data, logInButton.getText());
        ParseClient.getInstance().sendData(dataAndHead);

        String message = ParseClient.getInstance().readData();

        if (ParseClient.getInstance().decodeServerMessage(message)) {

            succesfullLog.setVisible(true);
            Navigation.getInstance().loadScreen("Profile");

            return;
        }

        couldNotLogIn.setVisible(true);

    }

    /**
     *
     * @param event
     */
    @FXML
    void onRegister(ActionEvent event) {

        if(clientSocket == null) {

            try {

                clientSocket = new Socket(hostField.getText(), Server.PORT);
                ParseClient.getInstance().setClientSocket(clientSocket);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(checkEmptyFields()){
            couldNotRegister.setText("Fill all fields");
            couldNotRegister.setVisible(true);
            return;
        }
        if(!checkEmailValidation(emailField.getText())){
            couldNotRegister.setText("Your email isn't in right format");
            couldNotRegister.setVisible(true);
            return;
        }

        String registerData = usernameField.getText() + " " + emailField.getText() + " " + passwordField.getText();
        System.out.println(logInButton.getText());

        String register = ParseClient.getInstance().setDataServer(registerData, logOutButton.getText());

        ParseClient.getInstance().sendData(register);

        String readData = ParseClient.getInstance().readData();

        if (ParseClient.getInstance().decodeServerMessage(readData)) {

            System.out.println("bem-vindo");
            succesfullRegister.setVisible(true);
            showLogin();

            return;
        }
        couldNotRegister.setVisible(true);

    }

    /**
     *
     * @return
     */
    private boolean checkEmailValidation(String email) {

        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        return email.matches(regex);

    }

    private boolean checkEmptyFields() {

        if (usernameField.getText().isEmpty()) {
            couldNotRegister.setVisible(true);
            return true;
        }

        if (passwordField.getText().isEmpty()) {
            couldNotRegister.setVisible(true);
            return true;
        }

        if (emailField.getText().isEmpty()) {
            couldNotRegister.setVisible(true);
            return true;
        }

        return false;
    }

    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        showLogin();

    }

    /**
     *
     */
    private void showLogin() {
        if (couldNotRegister.isVisible()) {
            couldNotRegister.setVisible(false);
        }
        succesfullRegister.setVisible(false);
        emailField.setVisible(false);
        emailText.setVisible(false);
        logOutButton.setVisible(false);
        alreadyHaveAccount.setVisible(false);
        logInButton.setVisible(true);
        dontHaveAccount.setVisible(true);

    }

    /**
     *
     */
    private void showRegister() {
        if (couldNotLogIn.isVisible()) {
            couldNotLogIn.setVisible(false);
        }

        emailField.setVisible(true);
        emailText.setVisible(true);
        logOutButton.setVisible(true);
        alreadyHaveAccount.setVisible(true);
        logInButton.setVisible(false);
        dontHaveAccount.setVisible(false);
    }
}

