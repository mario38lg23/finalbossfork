package com.rodasfiti.controllers;

<<<<<<< HEAD
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.rodasfiti.model.Escenario;
=======
>>>>>>> origin/Developer
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
        if (protagonista != null) {
            vida.setText(String.valueOf(protagonista.getVida()));
            ataque.setText(String.valueOf(protagonista.getAtaque()));
            escudo.setText(String.valueOf(protagonista.getDefensa()));
            nivel.setText(String.valueOf(protagonista.getNivel()));
        }
    }
<<<<<<< HEAD

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

                ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(rutaImagen)));
                imageView.setFitWidth(32);
                imageView.setFitHeight(32);
                gridPane.add(imageView, col, fila);
            }
        }
    }

=======
>>>>>>> origin/Developer
}