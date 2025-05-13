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
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
            SceneManager.getInstance().getStage().close();
        });
    }

    private void cargarMusica() {
        URL resource = getClass().getResource("/com/rodasfiti/media/final.mp3");
        if (resource != null) {
            Media sound = new Media(resource.toExternalForm());
            mediaPlayer = new MediaPlayer(sound);
            musica.setMediaPlayer(mediaPlayer); // Opcional, si usas MediaView
            mediaPlayer.play(); // Empieza sola
        } else {
            System.err.println("No se pudo cargar el recurso de audio.");
        }
    }

    public void reiniciarMusica() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        cargarMusica();
    }
}
