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

            mediaPlayer.setVolume(0); // Comenzar en volumen 0

            mediaPlayer.setOnPlaying(() -> {
                new Thread(() -> {
                    try {
                        double targetVolume = 0.6;
                        for (double vol = 0; vol <= targetVolume; vol += 0.02) {
                            double finalVol = vol;
                            javafx.application.Platform.runLater(() -> mediaPlayer.setVolume(finalVol));
                            Thread.sleep(100);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            });

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
