package com.rodasfiti.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.rodasfiti.model.Protagonista;
import com.rodasfiti.model.Proveedor;

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
        String vida2 = String.valueOf(protagonista.getVida());
        vida.setText(vida2);
        escudo.setText(String.valueOf(protagonista.getDefensa()));
        ataque.setText(String.valueOf(protagonista.getAtaque()));
        nivel.setText(String.valueOf(protagonista.getNivel()));

    }

}