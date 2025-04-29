package com.rodasfiti.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.rodasfiti.model.Escenario;
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
    private GridPane contenedorMapa;

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

    public void mostrarMapa(Escenario escenario, GridPane gridPane) {
        char[][] mapa = escenario.getMapa();

        for (int fila = 0; fila < mapa.length; fila++) {
            for (int col = 0; col < mapa[fila].length; col++) {
                Image imagen;
                if (mapa[fila][col] == 'S') {
                    imagen = new Image(getClass()
                            .getResource("src\\main\\resources\\com\\rodasfiti\\images\\pared2.png").toExternalForm());
                } else if (mapa[fila][col] == 'P') {
                    imagen = new Image(getClass()
                            .getResource("src\\main\\resources\\com\\rodasfiti\\images\\suelo2.png").toExternalForm());
                } else {
                    continue;
                }

                ImageView imageView = new ImageView(imagen);
                imageView.setFitWidth(32);
                imageView.setFitHeight(32);
                gridPane.add(imageView, col, fila);
            }
        }
    }

}