package com.rodasfiti.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.ColumnConstraints;

import com.rodasfiti.interfaces.Observer;
import com.rodasfiti.model.Escenario;
import com.rodasfiti.model.Personaje;
import com.rodasfiti.model.Protagonista;
import com.rodasfiti.model.Proveedor;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;


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

    @FXML
    private AnchorPane contenedorMapa;

    private GridPane mainGrid;

    @FXML
    public void initialize() {
        actualizarDatosProtagonista();
        mainGrid = new GridPane();
        mainGrid.setPadding(new Insets(10));
        mainGrid.setHgap(0);
        mainGrid.setVgap(0);
        AnchorPane.setTopAnchor(mainGrid, 0.0);
        AnchorPane.setLeftAnchor(mainGrid, 0.0);
        AnchorPane.setRightAnchor(mainGrid, 0.0);
        AnchorPane.setBottomAnchor(mainGrid, 0.0);

        // Cargar mapa desde CSV
        Escenario escenario = cargarEscenarioDesdeRecursos("/com/rodasfiti/data/mapa.csv");
        if (escenario != null) {
            mostrarMapa(escenario);
        } else {
            System.err.println("No se pudo cargar el escenario.");
        }

        contenedorMapa.getChildren().add(mainGrid);
    }

    void actualizarDatosProtagonista() {
        Protagonista protagonista = Proveedor.getInstance().getProtagonista();
        nombrePersonaje.setText(protagonista.getNombre());
        vida.setText(String.valueOf(protagonista.getVida()));
        ataque.setText(String.valueOf(protagonista.getAtaque()));
        escudo.setText(String.valueOf(protagonista.getDefensa()));
        nivel.setText(String.valueOf(protagonista.getNivel()));
    }

    private Escenario cargarEscenarioDesdeRecursos(String ruta) {
        try {
            URL resource = getClass().getResource(ruta);
            if (resource == null) {
                System.err.println("No se encontró el archivo: " + ruta);
                return null;
            }
            Path path = Paths.get(resource.toURI());
            return Escenario.cargarDesdeCSV(path);
        } catch (Exception e) {
            System.err.println("Error al cargar el escenario: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private void mostrarMapa(Escenario escenario) {
        char[][] mapa = escenario.getMapa();
        mainGrid.getChildren().clear();

        for (int fila = 0; fila < mapa.length; fila++) {
            for (int col = 0; col < mapa[fila].length; col++) {
                String rutaImagen;
                switch (mapa[fila][col]) {
                    case 'P':
                        rutaImagen = "/com/rodasfiti/images/pared2.png";
                        break;
                    case 'S':
                        rutaImagen = "/com/rodasfiti/images/suelo2.png";
                        break;
                    default:
                        rutaImagen = "/com/rodasfiti/images/suelo2.png"; 
                }

                Image image = cargarImagen(rutaImagen);
                if (image != null) {
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(96);
                    imageView.setFitHeight(67);
                    mainGrid.add(imageView, col, fila);
                }
            }
        }
    }

    private Image cargarImagen(String ruta) {
        try {
            return new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
        } catch (Exception e) {
            System.err.println("No se pudo cargar la imagen: " + ruta);
            return null;
        }
    }
}