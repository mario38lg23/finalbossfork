package com.rodasfiti.controllers;

import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.Media;

import java.net.URL;

import com.rodasfiti.SceneID;
import com.rodasfiti.SceneManager;

import javafx.fxml.FXML;

public class finalJuego {
    @FXML
    private Button salir;
    @FXML
    private Button volverAjugar;

    @FXML
    private MediaView musica;

    @FXML
    private MediaPlayer mediaPlayer;

    @FXML
    public void initialize() {
        volverAjugar.setOnAction(event -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
            SceneManager.getInstance().loadScene(SceneID.MAINVISTA);
        });
        salir.setOnAction(event -> {
            SceneManager.getInstance().getStage().close();
        });
        cargarMusica();
    }

    private void cargarMusica() {
        URL resource = getClass().getResource("/com/rodasfiti/media/final.mp3");
        if (resource != null) {
            Media sound = new Media(resource.toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        } else {
            System.err.println("No se pudo cargar el recurso de audio.");
        }
    }

}
