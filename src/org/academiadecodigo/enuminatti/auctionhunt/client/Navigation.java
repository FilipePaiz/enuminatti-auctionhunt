package org.academiadecodigo.enuminatti.auctionhunt.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.academiadecodigo.enuminatti.auctionhunt.server.Server;
import org.academiadecodigo.enuminatti.auctionhunt.server.ServiceRegistry;
import org.academiadecodigo.enuminatti.auctionhunt.server.User;
import org.academiadecodigo.enuminatti.auctionhunt.server.UserService;
import org.academiadecodigo.enuminatti.auctionhunt.utils.Security;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;

public final class Navigation {

    // static instance of this class
    private static Navigation instance = null;
    private LinkedList<Scene> scenes = new LinkedList<Scene>(); // Navigation History
    private Map<String, Controller> controllers = new HashMap<>(); //Container of controllers
    private final String VIEW_PATH = "org/academiadecodigo/enuminatti/auctionhunt/view/";
    private FXMLLoader fxmlLoader;
    private Stage stage; // reference to the application window

    // private constructor so it's not possible to instantiate from outside
    private Navigation() {
    }

    // static method that returns the instance
    public static Navigation getInstance() {

        if(instance == null){
            synchronized (Navigation.class) {
                if (instance == null) {
                    return instance = new Navigation();
                }
            }
        }
        return instance;
    }

    public void loadScreen(String view) {

        try {

            // Instantiate the view and the controller
            fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(VIEW_PATH + view + ".fxml"));
            Parent root = fxmlLoader.load();

            stage.setTitle("AuctionHunt");

            //Store the controller
            controllers.put(view, fxmlLoader.<Controller>getController());

            // Create a new scene and add it to the stack
            Scene scene = new Scene(root);
            scenes.push(scene);

            // Put the scene on the stage
            setScene(scene);

        } catch (IOException e) {
            System.out.println("Failure to load view " + view + " : " + e.getMessage());
        }
    }

    public void back() {

        if (scenes.isEmpty()) {
            return;
        }

        // remove the current scene from the stack
        scenes.pop();

        // load the scene at the top of the stack
        setScene(scenes.peek());
    }

    private void setScene(Scene scene) {

        // set the scene
        stage.setScene(scene);

        // show the stage to reload
        stage.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Controller getController() {
        return fxmlLoader.getController();
    }

    public static class LogicController implements Initializable {

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
                getInstance().loadScreen("Profile");
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

            System.out.println("-----------" + userService + "---------------");

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
}
