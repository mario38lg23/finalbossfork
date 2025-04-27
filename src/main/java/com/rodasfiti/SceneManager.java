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
    public void init(Stage stage, String styles) {
        this.stage = stage;
    }

    public void setScene(SceneID sceneID, String fxml) {
        // Obtener la pantalla principal
        Screen screen = Screen.getPrimary();

        // Obtener el tamaño de la pantalla
        double screenWidth = screen.getBounds().getWidth();
        double screenHeight = screen.getBounds().getHeight();
        try {
            // Carga el archivo FXML
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/" + fxml));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, screenWidth * 0.7, screenHeight * 0.7); // Crea la escena con el tamaño
                                                                                  // especificado
            scene.getStylesheets().add(styles.toExternalForm()); // Añade la hoja de estilo
            scenes.put(sceneID, scene); // Almacena la escena en el mapa con el identificador correspondiente
        } catch (IOException e) {
            e.printStackTrace(); // En caso de error al cargar el FXML
        }
    }

    public void removeScene(SceneID sceneID) {
        scenes.remove(sceneID); // Elimina la escena del mapa
    }

    public void loadScene(SceneID sceneID) {
        if (scenes.containsKey(sceneID)) {
            stage.setScene(scenes.get(sceneID)); // Establece la escena en la ventana principal
            stage.show(); // Muestra la ventana con la nueva escena
        }
    }
}