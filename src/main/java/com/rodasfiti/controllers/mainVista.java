package com.rodasfiti.controllers;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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
    private Label labelEscudo;
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

    private static final int MAX_PUNTOS = 15;

    @FXML
    public void initialize() {
        // contenedorPrincipal.heightProperty().addListener((observable, oldValue,
        // newValue) -> ajustarImagen());
        // Listeners para actualizar progreso y controlar la suma
        SliderVida.valueProperty()
                .addListener((obs, oldVal, newVal) -> manejarCambioSlider(SliderVida, oldVal, newVal));
        SliderAtaque.valueProperty()
                .addListener((obs, oldVal, newVal) -> manejarCambioSlider(SliderAtaque, oldVal, newVal));
        sliderDefensa.valueProperty()
                .addListener((obs, oldVal, newVal) -> manejarCambioSlider(sliderDefensa, oldVal, newVal));
        labelAtaque.setText("Ataque: " + (int) SliderAtaque.getValue());
        labelVida.setText("Vida: " + (int) SliderVida.getValue());
        labelEscudo.setText("Defensa: " + (int) sliderDefensa.getValue());

        if (nombrePersonaje != null) {
            nombrePersonaje.textProperty().addListener((observable, oldValue, newValue) -> actualizarProtagonista());
            actualizarProtagonista();
        }

        botonJugar.setOnAction(event -> {
            // Carga la escena secundaria cuando el botón es presionado
            SceneManager.getInstance().loadScene(SceneID.JUEGO);
        });
    }

    private void manejarCambioSlider(Slider sliderModificado, Number oldVal, Number newVal) {
        int vida = (int) SliderVida.getValue();
        int ataque = (int) SliderAtaque.getValue();
        int defensa = (int) sliderDefensa.getValue();

        int total = vida + ataque + defensa;

        if (total > MAX_PUNTOS) {
            // Si supera el máximo, revertimos el cambio en este slider
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
        int nivel = 1; // Asignar un valor inicial para el nivel, si es necesario

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
