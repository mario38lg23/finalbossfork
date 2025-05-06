package com.rodasfiti.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import com.rodasfiti.interfaces.Observer;
import com.rodasfiti.model.Escenario;
import com.rodasfiti.model.Personaje;
import com.rodasfiti.model.Protagonista;
import com.rodasfiti.model.Proveedor;


public class VistaJuego implements Observer{

    @FXML
    private Label vida;
    @FXML
    private Label escudo;
    @FXML
    private Label ataque;
    @FXML
    private Label nivel;
    @FXML
    private Label nombrePersonaje;

    GridPane mapa;
    Personaje personaje;

    @FXML
    public void initialize() {
        mapa = new GridPane();
        actualizarDatosProtagonista();
        mostrarMapa(null, mapa);
    }

    public void actualizarDatosProtagonista() {
        Protagonista protagonista = Proveedor.getInstance().getProtagonista();
        nombrePersonaje.setText(protagonista.getNombre());
        vida.setText(String.valueOf(protagonista.getVida()));
        ataque.setText(String.valueOf(protagonista.getAtaque()));
        escudo.setText(String.valueOf(protagonista.getDefensa()));
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

                ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(rutaImagen)));
                imageView.setFitWidth(32);
                imageView.setFitHeight(32);
                gridPane.add(imageView, col, fila);
            }
        }
    }

    @Override
    public void onChange() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onChange'");
    }

}