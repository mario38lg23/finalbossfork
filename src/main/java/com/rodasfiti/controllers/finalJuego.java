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
        salir.setOnAction(event->{
            SceneManager.getInstance().getStage().close();
        });
        cargarMusica();
    }
    private void cargarMusica() {
        try {
            // Ruta fija (recomendado si sabes el nombre del archivo)
            String rutaAudio = "/com/rodasfiti/media/Lord_of_the_Rings_Sound_of_The_Shire.mp3";

            URL url = getClass().getResource(rutaAudio);

            if (url == null) {
                System.err.println("Archivo de audio no encontrado: " + rutaAudio);
            } else {
                Media media = new Media(url.toExternalForm());
                this.mediaPlayer = new MediaPlayer(media); // Asignamos la instancia de MediaPlayer al campo
                musica.setMediaPlayer(this.mediaPlayer); // Usamos el mediaPlayer de la clase
                this.mediaPlayer.setAutoPlay(true);
                this.mediaPlayer.setVolume(0.6);
                this.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                this.mediaPlayer.play();
            }
        } catch (Exception e) {
            System.err.println("Error al cargar audio: " + e.getMessage());
        }
    }
    

}
