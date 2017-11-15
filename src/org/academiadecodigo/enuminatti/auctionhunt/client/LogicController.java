package org.academiadecodigo.enuminatti.auctionhunt.client;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.academiadecodigo.enuminatti.auctionhunt.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class LogicController implements Initializable {

    @FXML
    private Button logOutButton;

    private Service<Void> readThread;

    private Socket clientSocket;

    @FXML
    private PasswordField passwordField;

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
    private TextField emailField;

    private ClientWorker clientWorker;

    @FXML
    void changeToLogin(ActionEvent event) {
        showLogin();
    }

    @FXML
    void changeToRegister(ActionEvent event) {
        showRegister();
    }

    /**
     * @param event
     */
    @FXML
    void onLogin(ActionEvent event) {

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

        clientWorker.start();
    }

    /**
     * @param event
     */
    @FXML
    void onRegister(ActionEvent event) {

        if (checkEmptyFields()) {
            couldNotRegister.setText("Fill all fields");
            couldNotRegister.setVisible(true);
            return;
        }
        if (!checkEmailValidation(emailField.getText())) {
            couldNotRegister.setText("Your email isn't in right format");
            couldNotRegister.setVisible(true);
            return;
        }

        String registerData = usernameField.getText() + " " + emailField.getText() + " " + passwordField.getText();
        System.out.println(logInButton.getText());

        String register = ParseClient.getInstance().setDataServer(registerData, logOutButton.getText());

        ParseClient.getInstance().sendData(register);

        clientWorker.start();

    }

    /**
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
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            clientSocket = new Socket(Server.HOST, Server.PORT);

        } catch (IOException e) {
            e.printStackTrace();
        }

        ParseClient.getInstance().setClientSocket(clientSocket);
        clientWorker = new ClientWorker();
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

    private class ClientWorker extends Service<Void> {


        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {

                @Override
                protected Void call() throws Exception {
                    System.out.println("Read data thread " + Thread.currentThread().getName());

                    while (true) {
                        handleRead();
                        clientWorker.restart();
                        clientWorker.ready();
                    }
                }
            };
        }

        private void handleRead() {

            String line = null;

            try {

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                line = in.readLine();
                System.out.println(line);


                if (ParseClient.getInstance().decodeServerMessage(line).startsWith("login")) {

                    succesfullLog.setVisible(true);
                    Navigation.getInstance().loadScreen("Profile");
                    clientWorker.restart();
                    return;
                }

                couldNotLogIn.setVisible(true);

                if (ParseClient.getInstance().decodeServerMessage(line).startsWith("register")) {
                    System.out.println("bem-vindo");
                    succesfullRegister.setVisible(true);
                    showLogin();
                    clientWorker.restart();

                    return;
                }
                couldNotRegister.setVisible(true);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

