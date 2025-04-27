package com.rodasfiti;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SceneManager {

    private static SceneManager instance;

    private Stage stage;
    private URL styles;
    private HashMap<SceneID, Scene> scenes;

    private SceneManager() {
        scenes = new HashMap<>();
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    @SuppressWarnings("exports")
    public void init(Stage stage, String styles){
        this.stage = stage;
        this.styles = App.class.getResource("styles/" + styles + ".css");
    }

    @SuppressWarnings("exports")
    public void init(Stage stage){
        this.stage = stage;
    }


    public void setScene(SceneID sceneID, String fxml) {
        Screen screen = Screen.getPrimary();

        double screenWidth = screen.getBounds().getWidth();
        double screenHeight = screen.getBounds().getHeight();
        try {
            URL url = App.class.getResource("views/" + fxml + ".fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, screenWidth*0.7, screenHeight*0.7);
            if (styles!=null) scene.getStylesheets().add(styles.toExternalForm());
            scenes.put(sceneID, scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeScene(SceneID sceneID) {
        scenes.remove(sceneID);
    }

    public void loadScene(SceneID sceneID) {
        if (scenes.containsKey(sceneID)) {
            stage.setScene(scenes.get(sceneID));
            stage.show();
        }
    }

    @SuppressWarnings("exports")
    public Scene getScene(SceneID sceneID){
        if (scenes.containsKey(sceneID)){
            Scene scene = scenes.get(sceneID);
            FXMLLoader loader = (FXMLLoader) scene.getUserData(); // Recuperar el loader
            return loader.getController();
        } else {
            System.err.println("La escena seleccionada no existe");
            return null;
        }
    }
}