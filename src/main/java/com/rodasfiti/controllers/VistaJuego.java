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
    private GridPane mapa;

    @FXML
    public void initialize() {

        actualizarDatosProtagonista();
        Path rutaMapa = Paths.get("src/main/resources/com/rodasfiti/data/mapa.csv");
        Escenario escenario = Escenario.cargarDesdeCSV(rutaMapa);

        if (escenario != null) {

            for (char[] fila : escenario.getMapa()) {
                System.out.println(java.util.Arrays.toString(fila));
            }
            mostrarMapa(escenario, mapa);
        } else {
            System.out.println("Error al cargar el mapa.");
        }

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

        gridPane.getChildren().clear();

        for (int fila = 0; fila < mapa.length; fila++) {
            for (int col = 0; col < mapa[fila].length; col++) {
                String rutaImagen;

                if (mapa[fila][col] == 'P') {
                    rutaImagen = "/com/rodasfiti/images/pared2.png";
                } else if (mapa[fila][col] == 'S') {
                    rutaImagen = "/com/rodasfiti/images/suelo2.png";
                } else {
                    continue;
                }
            }
        }
    }

}