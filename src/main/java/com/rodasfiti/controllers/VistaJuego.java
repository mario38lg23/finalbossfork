package com.rodasfiti.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import com.rodasfiti.interfaces.Observer;
import com.rodasfiti.model.*;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

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
    private Label movimientosFaltantes;

    @FXML
    private AnchorPane contenedorMapa;

    private GridPane mainGrid;
    private int posX = 1;
    private int posY = 1;
    private ImageView protagonistaView;
    private Escenario escenario;
    private int movimientosRestantes = 15;
    private static Random r = new Random();
    private ArrayList<Enemigo> listaEnemigos = new ArrayList<>();
    private ArrayList<ImageView> vistasEnemigos = new ArrayList<>(); // Añadida esta línea

    @FXML
    public void initialize() {
        actualizarDatosProtagonista();
        movimientosFaltantes.setText(String.valueOf(movimientosRestantes));
        mainGrid = new GridPane();
        mainGrid.setPadding(new Insets(10));
        mainGrid.setHgap(0);
        mainGrid.setVgap(0);
        AnchorPane.setTopAnchor(mainGrid, 0.0);
        AnchorPane.setLeftAnchor(mainGrid, 0.0);
        AnchorPane.setRightAnchor(mainGrid, 0.0);
        AnchorPane.setBottomAnchor(mainGrid, 0.0);
        
        escenario = cargarEscenarioDesdeRecursos("/com/rodasfiti/data/mapaprueba.csv");
        if (escenario != null) {
            listaEnemigos = GestorEnemigos.cargarEnemigosDesdeCSV("/com/rodasfiti/data/enemigos.csv");
            mostrarMapa();
            spawnEnemigos(3);
            
        } else {
            System.err.println("No se pudo cargar el escenario.");
        }

        contenedorMapa.getChildren().add(mainGrid);
        mover();
        moverEnemigos();
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

    private void mostrarMapa() {
        char[][] mapa = escenario.getMapa();
        mainGrid.getChildren().clear();
        vistasEnemigos.clear();
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
        mostrarEnemigos(ancho, alto);
    }

    private void spawnEnemigos(int cantidad) {
        char[][] mapa = escenario.getMapa();
        int filas = mapa.length;
        int columnas = mapa[0].length;
        int nuevoX;
        int nuevoY;
        if (listaEnemigos == null || listaEnemigos.isEmpty()) {
            listaEnemigos = GestorEnemigos.cargarEnemigosDesdeCSV("/com/rodasfiti/data/enemigos.csv");
        }
        ArrayList<Enemigo> enemigosASpawn = new ArrayList<>();
        for (int i = 0; i < cantidad && i < listaEnemigos.size(); i++) {
            Enemigo enemigoBase = listaEnemigos.get(i);
            int intentos = 0;
            boolean ubicado = false;
            while (intentos < 30 && !ubicado) {
                if(posX + r.nextInt(10) > filas){
                    nuevoX = posX - r.nextInt(10);
                }else nuevoX = posX + r.nextInt(10);
                if(posY + r.nextInt(10) > columnas){
                    nuevoY = posY - r.nextInt(10);
                }else nuevoY = posY + r.nextInt(10);
                if (esPosicionValida(nuevoX, nuevoY)) {
                    Enemigo enemigo = new Enemigo(
                        enemigoBase.getTipo(),
                        enemigoBase.getNivel(),
                        enemigoBase.getAtaque(),
                        enemigoBase.getDefensa(),
                        enemigoBase.getVida(),
                        enemigoBase.getVelocidad(),
                        enemigoBase.getPercepcion(),
                        nuevoX,
                        nuevoY
                    );
                    enemigosASpawn.add(enemigo);
                    ubicado = true;
                }
                intentos++;
            }
        }
        listaEnemigos.clear();
        vistasEnemigos.clear();
        listaEnemigos.addAll(enemigosASpawn);
        mostrarMapa();
    }



    private boolean esPosicionValida(int x, int y) {
    return x >= 0 && y >= 0 && x < escenario.getMapa().length && y < escenario.getMapa()[0].length && puedeMoverA(x, y) && !(x == posX && y == posY);
    }


    private void mostrarEnemigos(int ancho, int alto) {
        for (Enemigo enemigo : listaEnemigos) {
            if (puedeMoverA(enemigo.getX(), enemigo.getY())) {
                String imagen = "/com/rodasfiti/images/";
                switch (enemigo.getTipo()) {
                    case ORCO:
                        imagen += "orco.png";
                        break;
                    case DUENDE:
                        imagen += "duende.png";
                        break;
                    case ESQUELETO:
                        imagen += "esqueleto.png";
                        break;
                    default:
                        imagen += "duende.png";
                        break;
                }

                try {
                    Image enemigoImagen = cargarImagen(imagen);
                    ImageView enemigoView = new ImageView(enemigoImagen);
                    enemigoView.setFitWidth(ancho);
                    enemigoView.setFitHeight(alto);
                    mainGrid.add(enemigoView, enemigo.getY(), enemigo.getX());
                    vistasEnemigos.add(enemigoView);
                    System.out.println("Enemigo añadido en: X=" + enemigo.getX() + ", Y=" + enemigo.getY() + ", Tipo=" + enemigo.getTipo());
                } catch (Exception e) {
                    System.err.println("Error al cargar imagen de enemigo: " + imagen);
                    e.printStackTrace();
                }
            }
        }
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
            mostrarMapa();
            movimientosRestantes--;
            movimientosFaltantes.setText(String.valueOf(movimientosRestantes));
        }
    }

    private void moverEnemigos() {
    for (Enemigo enemigo : listaEnemigos) {
        int actualX = enemigo.getX();
        int actualY = enemigo.getY();
        int nuevaX = actualX;
        int nuevaY = actualY;
        if (actualX < posX) {
            nuevaX++;
        } else if (actualX > posX) {
            nuevaX--;
        }
        if (actualY < posY) {
            nuevaY++;
        } else if (actualY > posY) {
            nuevaY--;
        }
        if (puedeMoverA(nuevaX, nuevaY)) {
            enemigo.setX(nuevaX);
            enemigo.setY(nuevaY);
        }
    }
}

    private boolean puedeMoverA(int x, int y) {
        return escenario.getMapa()[x][y] == 'S';
    }

    private Image cargarImagen(String ruta) {
        try {
            return new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
        } catch (Exception e) {
            System.err.println("No se pudo cargar la imagen: " + ruta);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onChange() {
        actualizarDatosProtagonista();

    }
}