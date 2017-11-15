package org.academiadecodigo.enuminatti.auctionhunt.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public final class Navigation {


    /**
     *
     */
    // static instance of this class\
    private static Navigation instance = null;
    private LinkedList<Scene> scenes = new LinkedList<Scene>(); // Navigation History
    private Map<String, Controller> controllers = new HashMap<>(); //Container of controllers
    private final String VIEW_PATH = "org/academiadecodigo/enuminatti/auctionhunt/view/";
    private FXMLLoader fxmlLoader;
    private Stage stage; // reference to the application window
    public static Parent root;
    // private constructor so it's not possible to instantiate from outside

    /**
     *
     */
    private Navigation() {
    }

    // static method that returns the instance

    /**
     * @return
     */
    public static Navigation getInstance() {

        if (instance == null) {
            synchronized (Navigation.class) {
                if (instance == null) {
                    return instance = new Navigation();
                }
            }
        }
        return instance;
    }

    /**
     * @param view
     */
    public void loadScreen(String view) {

        try {

            // Instantiate the view and the controller
            fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(VIEW_PATH + view + ".fxml"));
             root = fxmlLoader.load();

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

    /**
     *
     */
    public void back() {

        if (scenes.isEmpty()) {
            return;
        }

        // remove the current scene from the stack
        scenes.pop();

        // load the scene at the top of the stack
        setScene(scenes.peek());
    }

    /**
     * @param scene
     */
    private void setScene(Scene scene) {

        // set the scene
        stage.setScene(scene);

        // show the stage to reload
        stage.show();
    }

    /**
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * @return
     */
    public Controller getController() {
        return fxmlLoader.getController();
    }
}
