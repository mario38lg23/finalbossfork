package com.rodasfiti.controllers;

import com.rodasfiti.model.Protagonista;
import com.rodasfiti.model.Proveedor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class VistaJuego {

    @FXML
    private Label vida;
    @FXML
    private Label escudo;
    @FXML
    private Label ataque;
    @FXML
    private Label nivel;

    @FXML
    public void initialize() {
        actualizarDatosProtagonista();
    }
    
    public void actualizarDatosProtagonista() {
        Protagonista protagonista = Proveedor.getInstance().getProtagonista();
        if (protagonista != null) {
            vida.setText(String.valueOf(protagonista.getVida()));
            ataque.setText(String.valueOf(protagonista.getAtaque()));
            escudo.setText(String.valueOf(protagonista.getDefensa()));
            nivel.setText(String.valueOf(protagonista.getNivel()));
        }
    }
}