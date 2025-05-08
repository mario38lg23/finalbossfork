package com.rodasfiti.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import com.rodasfiti.interfaces.Observer;
import com.rodasfiti.model.Escenario;
import com.rodasfiti.model.Protagonista;
import com.rodasfiti.model.Proveedor;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class VistaJuego implements Observer {

    @FXML
    private Label vida;

    @FXML
    private Label velocidad;

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
    private int posX = 1;
    private int posY = 1;
    private ImageView protagonistaView;
    private Escenario escenario;
    private int anchoCelda;
    private int altoCelda;

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
        escenario = cargarEscenarioDesdeRecursos("/com/rodasfiti/data/mapa2.csv");
        if (escenario != null) {
            mostrarMapa(escenario);
        } else {
            System.err.println("No se pudo cargar el escenario.");
        }

        contenedorMapa.getChildren().add(mainGrid);
        mover();

    }

    void actualizarDatosProtagonista() {
        Protagonista protagonista = Proveedor.getInstance().getProtagonista();
        nombrePersonaje.setText(protagonista.getNombre());
        vida.setText(String.valueOf(protagonista.getVida()));
        ataque.setText(String.valueOf(protagonista.getAtaque()));
        escudo.setText(String.valueOf(protagonista.getDefensa()));
        velocidad.setText(String.valueOf(protagonista.getVelocidad()));
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

    private void mostrarMapa(Escenario esc) {
        char[][] mapa = esc.getMapa();
        mainGrid.getChildren().clear();

        int filas = mapa.length;
        int columnas = mapa[0].length;

        int ancho = 973 / columnas;
        int alto = 673 / filas;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                String imagen = "/com/rodasfiti/images/";
                if (mapa[i][j] == 'P') {
                    imagen += "pared.png";
                } else {
                    imagen += "suelo.png";
                }

                ImageView bloque = new ImageView(cargarImagen(imagen));
                bloque.setFitWidth(ancho);
                bloque.setFitHeight(alto);
                mainGrid.add(bloque, j, i);
            }
        }

        if (protagonistaView == null) {
            protagonistaView = new ImageView(cargarImagen("/com/rodasfiti/images/caballero_abajo.png"));
            protagonistaView.setFitWidth(ancho);
            protagonistaView.setFitHeight(alto);
        }

        mainGrid.add(protagonistaView, posY, posX);
    }

    private void mover() {
        contenedorMapa.setFocusTraversable(true);
        contenedorMapa.requestFocus();
        contenedorMapa.setOnKeyPressed(this::moverProtagonista);
    }

    private void moverProtagonista(KeyEvent e) {
        int nuevaX = posX;
        int nuevaY = posY;
        String img = "/com/rodasfiti/images/";

        switch (e.getCode()) {
            case W:
            case UP:
                nuevaX--;
                img += "caballero_arriba.png";
                break;

            case S:
            case DOWN:
                nuevaX++;
                img += "caballero_abajo.png";
                break;

            case A:
            case LEFT:
                nuevaY--;
                img += "caballero_izquierda.png";
                break;

            case D:
            case RIGHT:
                nuevaY++;
                img += "caballero_derecha.png";
                break;

            default:
                return;
        }

        if (puedeMoverA(nuevaX, nuevaY)) {
            posX = nuevaX;
            posY = nuevaY;
            protagonistaView.setImage(cargarImagen(img));
            mostrarMapa(escenario);
        }
    }

    private boolean puedeMoverA(int x, int y) {
        return escenario.getMapa()[x][y] == 'S';
    }

    private Image cargarImagen(String ruta) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
    }

    @Override
    public void onChange() {
        throw new UnsupportedOperationException("Unimplemented method 'onChange'");
    }
}