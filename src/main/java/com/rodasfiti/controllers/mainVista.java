package com.rodasfiti.controllers;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

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

public class mainVista {

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
    private StackPane fondoImagen;

    private static final int MAX_PUNTOS = 15;

    @FXML
    public void initialize() {
        // contenedorPrincipal.heightProperty().addListener((observable, oldValue,
        // newValue) -> ajustarImagen());
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

            SceneManager.getInstance().loadScene(SceneID.JUEGO);
        });

        fondoImagen.setStyle(
            "-fx-background-image: url('/images/fondo2(1).jpg');" +
            "-fx-background-size: cover;" +
            "-fx-background-repeat: no-repeat;" +
            "-fx-background-position: center center;"
        );
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
        int vida = (int) SliderVida.getValue();
        int ataque = (int) SliderAtaque.getValue();
        int defensa = (int) sliderDefensa.getValue();
        int atributos = (int) Math.round(porcentajeAtributos.getProgress() * MAX_PUNTOS);
        int nivel = 1;

        Protagonista protagonista = new Protagonista(nombre, vida, ataque, defensa, atributos, nivel);
        Proveedor.getInstance().setProtagonista(protagonista);
    }

    /*
     * private void ajustarImagen() {
     * ajustarImagenIndividual(imagePersonaje);
     * ajustarImagenIndividual(imageAtaque);
     * ajustarImagenIndividual(imageVida);
     * ajustarImagenIndividual(imageDefensa);
     * }
     * 
     * private void ajustarImagenIndividual(ImageView imagenView) {
     * if (imagenView.getImage() == null) {
     * return;
     * }
     * double contenedorHeight = contenedorPrincipal.getHeight();
     * double contenedorWidth = contenedorPrincipal.getWidth();
     * 
     * double imageWidth = imagenView.getImage().getWidth();
     * double imageHeight = imagenView.getImage().getHeight();
     * double imageAspectRatio = imageWidth / imageHeight;
     * 
     * double maxWidth = contenedorWidth * 0.3; // Ajusta el % máximo que puede
     * ocupar
     * double maxHeight = contenedorHeight * 0.3;
     * 
     * if (maxWidth / imageAspectRatio <= maxHeight) {
     * imagenView.setFitWidth(maxWidth);
     * imagenView.setFitHeight(maxWidth / imageAspectRatio);
     * } else {
     * imagenView.setFitHeight(maxHeight);
     * imagenView.setFitWidth(maxHeight * imageAspectRatio);
     * }
     * }
     */

}
