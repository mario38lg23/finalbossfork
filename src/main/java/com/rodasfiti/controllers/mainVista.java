package com.rodasfiti.controllers;

import javafx.scene.image.ImageView;

import com.rodasfiti.SceneID;
import com.rodasfiti.SceneManager;
import com.rodasfiti.model.Protagonista;
import com.rodasfiti.model.Proveedor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class mainVista {
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

    @FXML
    private void onJugarButtonClick() {
        String nombre = nombrePersonaje.getText();
        int vida = (int) SliderSalud.getValue();
        int ataque = (int) SliderAtaque.getValue();
        int defensa = (int) sliderSalud.getValue();

        Protagonista protagonista = new Protagonista(nombre, vida, ataque, defensa);

        Proveedor.getInstance().setProtagonista(protagonista);

    }
}
