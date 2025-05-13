package com.rodasfiti;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal que lanza la aplicación FinalBoss.
 * 
 * @author Mario López, Manuel Rodas
 */

public class App extends Application {

    @SuppressWarnings("exports")
    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("FinalBoss");
        stage.getIcons().add(new Image(App.class.getResource("images/corazon.png").toExternalForm()));

        SceneManager sm = SceneManager.getInstance();

        sm.init(stage, "css");

        sm.setScene(SceneID.MAINVISTA, "paginaInicio");
        sm.setScene(SceneID.JUEGO, "VistaJuego");
        sm.setScene(SceneID.FINAL, "finalJuego");

        sm.loadScene(SceneID.MAINVISTA);
    }

    public static void main(String[] args) {
        launch();
    }
}