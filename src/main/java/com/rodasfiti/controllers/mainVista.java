package com.rodasfiti.controllers;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import com.rodasfiti.model.Protagonista;
import com.rodasfiti.model.Proveedor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class mainVista {

    @FXML
    private AnchorPane contenedorPrincipal;
    @FXML
    private ImageView imagePersonaje;

    @FXML
    private Slider SliderSalud;

    @FXML
    private ImageView imageVida;

    @FXML
    private ImageView imageSalud;

    @FXML
    private Slider sliderSalud;

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

    /*@FXML
    private void initialize() {
        String nombre = nombrePersonaje.getText();
        int vida = (int) SliderSalud.getValue();
        int ataque = (int) SliderAtaque.getValue();
        int defensa = (int) sliderSalud.getValue();
        int atributos = (int) porcentajeAtributos.getProgress() * 100;


        Protagonista protagonista = new Protagonista(nombre, vida, ataque, defensa, atributos);

        Proveedor.getInstance().setProtagonista(protagonista);

    }


    /*private void ajustarImagen() {
        // Obtiene la altura del VBox (contenedor principal)
        double height = hBox.getHeight();
        double width = hBox.getWidth();
        double infoWidth = vBox.getPrefWidth();
        double aspectRatio = imageView.getImage().getWidth()/imageView.getImage().getHeight();

        // Ajusta la imagen dependiendo de la proporción del contenedor y el aspect ratio de la imagen
        if (width-infoWidth>height*aspectRatio) {
            imageView.setFitHeight(height-10); // Cuando no cabe a lo alto, ajusta la altura de la imagen
        } else {
            imageView.setFitWidth(width-infoWidth-10);  // Cuando no cabe a lo ancho, ajusta la anchura de la imagen
        }
    }*/
}
