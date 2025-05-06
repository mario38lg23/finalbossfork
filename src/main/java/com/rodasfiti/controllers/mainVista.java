package com.rodasfiti.controllers;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.Media;

import java.net.URL;
import java.util.Observer;
import com.rodasfiti.SceneID;
import com.rodasfiti.SceneManager;
import com.rodasfiti.model.Protagonista;
import com.rodasfiti.model.Proveedor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class mainVista{

    @FXML
    private Label labelVida;

    @FXML
    private Label labelDefensa;

    @FXML
    private Label labelAtaque;

    @FXML
    private AnchorPane contenedorPrincipal;

    @FXML
    private ImageView imagePersonaje;

    @FXML
    private Slider SliderVida;

    @FXML
    private ImageView imageVida;

    @FXML
    private ImageView imageDefensa;

    @FXML
    private Slider sliderDefensa;

    @FXML
    private ImageView imageAtaque;

    @FXML
    private TextField nombrePersonaje;

    @FXML
    private Slider SliderAtaque;

    @FXML
    private ProgressIndicator porcentajeAtributos;

    @FXML
    private Button botonJugar;

    @FXML
    private MediaView musica;

    @FXML
    private MediaPlayer mediaPlayer;

    @FXML
    private StackPane fondoImagen;

    @FXML
    private ImageView fondoCastillo;

    private static final int MAX_PUNTOS = 15;

    @FXML
    public void initialize() {
        SliderVida.valueProperty().addListener((obs, oldVal, newVal) -> {
            int valor = newVal.intValue();
            labelVida.setText(String.valueOf(valor));
            manejarCambioSlider(SliderVida, oldVal, newVal);
        });

        SliderAtaque.valueProperty().addListener((obs, oldVal, newVal) -> {
            int valor = newVal.intValue();
            labelAtaque.setText(String.valueOf(valor));
            manejarCambioSlider(SliderAtaque, oldVal, newVal);
        });

        sliderDefensa.valueProperty().addListener((obs, oldVal, newVal) -> {
            int valor = newVal.intValue();
            labelDefensa.setText(String.valueOf(valor));
            manejarCambioSlider(sliderDefensa, oldVal, newVal);
        });

        if (nombrePersonaje != null) {
            nombrePersonaje.textProperty().addListener((observable, oldValue, newValue) -> actualizarProtagonista());
            actualizarProtagonista();
        }
        botonJugar.setOnAction(event -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();  // Detener la música al cambiar de escena
            }
            actualizarProtagonista();  // Actualizar los datos del protagonista
            SceneManager.getInstance().loadScene(SceneID.JUEGO);
            VistaJuego controlador = (VistaJuego) SceneManager.getInstance().getController(SceneID.JUEGO);
            if (controlador != null) {
                controlador.actualizarDatosProtagonista();
            }
        });
        cargarMusica();  // Cargar y reproducir la música de fondo
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
                this.mediaPlayer = new MediaPlayer(media);  // Asignamos la instancia de MediaPlayer al campo
                musica.setMediaPlayer(this.mediaPlayer);  // Usamos el mediaPlayer de la clase
                this.mediaPlayer.setAutoPlay(true);
                this.mediaPlayer.setVolume(0.6);
                this.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                this.mediaPlayer.play();
            }
        } catch (Exception e) {
            System.err.println("Error al cargar audio: " + e.getMessage());
        }
    }
    private void manejarCambioSlider(Slider sliderModificado, Number oldVal, Number newVal) {
        int vida = (int) SliderVida.getValue();
        int ataque = (int) SliderAtaque.getValue();
        int defensa = (int) sliderDefensa.getValue();
        int total = vida + ataque + defensa;
        if (total > MAX_PUNTOS) {
            sliderModificado.setValue(oldVal.doubleValue());
        } else {
            actualizarProgreso();
            actualizarProtagonista();
        }
    }

    private void actualizarProgreso() {
        int vida = (int) SliderVida.getValue();
        int ataque = (int) SliderAtaque.getValue();
        int defensa = (int) sliderDefensa.getValue();

        int total = vida + ataque + defensa;
        double progreso = Math.min(total, MAX_PUNTOS) / (double) MAX_PUNTOS;

        porcentajeAtributos.setProgress(progreso);
    }

    private void actualizarProtagonista() {
        String nombre = nombrePersonaje.getText();
        int vida = Integer.parseInt(labelVida.getText());
        int ataque = Integer.parseInt(labelAtaque.getText());
        int defensa = Integer.parseInt(labelDefensa.getText());
        int atributos = (int) Math.round(porcentajeAtributos.getProgress() * MAX_PUNTOS);
        int nivel = 1;

        Protagonista protagonista = new Protagonista(nombre, vida, ataque, defensa, atributos, nivel);
        Proveedor.getInstance().setProtagonista(protagonista);
    }

}
