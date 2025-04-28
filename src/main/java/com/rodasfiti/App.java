package com.rodasfiti;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @SuppressWarnings("exports")
    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("FinalBoss");

        SceneManager sm = SceneManager.getInstance();

        sm.init(stage, "css");

        sm.setScene(SceneID.MAINVISTA, "paginaInicio");
        sm.setScene(SceneID.JUEGO, "vista2");

        sm.loadScene(SceneID.MAINVISTA);
    }

    public static void main(String[] args) {
        launch();
    }
}