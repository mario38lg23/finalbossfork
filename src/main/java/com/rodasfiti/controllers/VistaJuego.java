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
    private ArrayList<ImageView> vistasEnemigos = new ArrayList<>();
    private Protagonista protagonista;

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
            protagonista = Proveedor.getInstance().getProtagonista();
            protagonista.setPosicionAleatoria(escenario);

            listaEnemigos = GestorEnemigos.cargarEnemigosDesdeCSV("/com/rodasfiti/data/enemigos.csv");
            mostrarMapa();
            // spawnEnemigos(10);
        } else {
            System.err.println("No se pudo cargar el escenario.");
        }
        contenedorMapa.getChildren().add(mainGrid);
        mover();
        // moverEnemigos();
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
        protagonistaView = new ImageView(cargarImagen("/com/rodasfiti/images/caballero_abajo.png"));
        protagonistaView.setFitWidth(ancho);
        protagonistaView.setFitHeight(alto);
        mainGrid.add(protagonistaView, protagonista.getColumna(), protagonista.getFila());
        // mostrarEnemigos(ancho, alto);
    }

    /*
     * private void spawnEnemigos(int cantidad) {
     * ArrayList<Enemigo> enemigosDesdeCSV =
     * GestorEnemigos.cargarEnemigosDesdeCSV("/com/rodasfiti/data/enemigos.csv");
     * ArrayList<Enemigo> enemigosASpawn = new ArrayList<>();
     * int enemigosCreados = 0;
     * 
     * // Agrupar enemigos por tipo
     * ArrayList<Enemigo> duendes = new ArrayList<>();
     * ArrayList<Enemigo> orcos = new ArrayList<>();
     * ArrayList<Enemigo> esqueletos = new ArrayList<>();
     * 
     * for (Enemigo enemigo : enemigosDesdeCSV) {
     * switch (enemigo.getTipo()) {
     * case DUENDE:
     * duendes.add(enemigo);
     * break;
     * case ORCO:
     * orcos.add(enemigo);
     * break;
     * case ESQUELETO:
     * esqueletos.add(enemigo);
     * break;
     * }
     * }
     * 
     * ArrayList<Enemigo> todos = new ArrayList<>();
     * todos.addAll(duendes);
     * todos.addAll(orcos);
     * todos.addAll(esqueletos);
     * 
     * Random r = new Random();
     * while (enemigosCreados < cantidad) {
     * Enemigo base = todos.get(r.nextInt(todos.size()));
     * 
     * // Buscar una posición aleatoria válida
     * int intentos = 0;
     * while (intentos < 100) {
     * int x = r.nextInt(escenario.getMapa().length);
     * int y = r.nextInt(escenario.getMapa()[0].length);
     * if (esPosicionValida(x, y)) {
     * enemigosASpawn.add(new Enemigo(
     * base.getTipo(),
     * base.getNivel(),
     * base.getAtaque(),
     * base.getDefensa(),
     * base.getVida(),
     * base.getVelocidad(),
     * base.getPercepcion(),
     * x, y));
     * enemigosCreados++;
     * break;
     * }
     * intentos++;
     * }
     * }
     * 
     * listaEnemigos.clear();
     * listaEnemigos.addAll(enemigosASpawn);
     * vistasEnemigos.clear();
     * mostrarMapa();
     * }
     */

    private boolean esPosicionValida(int x, int y) {
        return x >= 0 && y >= 0 && x < escenario.getMapa().length && y < escenario.getMapa()[0].length
                && puedeMoverA(x, y) && !(x == posX && y == posY);
    }

    /*
     * private void mostrarEnemigos(int ancho, int alto) {
     * for (Enemigo enemigo : listaEnemigos) {
     * if (puedeMoverA(enemigo.getX(), enemigo.getY())) {
     * String imagen = "/com/rodasfiti/images/";
     * switch (enemigo.getTipo()) {
     * case ORCO:
     * imagen += "orco.png";
     * break;
     * case DUENDE:
     * imagen += "duende.png";
     * break;
     * case ESQUELETO:
     * imagen += "esqueleto.png";
     * break;
     * default:
     * imagen += "duende.png";
     * break;
     * }
     * 
     * try {
     * Image enemigoImagen = cargarImagen(imagen);
     * ImageView enemigoView = new ImageView(enemigoImagen);
     * enemigoView.setFitWidth(ancho);
     * enemigoView.setFitHeight(alto);
     * mainGrid.add(enemigoView, enemigo.getY(), enemigo.getX());
     * vistasEnemigos.add(enemigoView);
     * System.out.println("Enemigo añadido en: X=" + enemigo.getX() + ", Y=" +
     * enemigo.getY() + ", Tipo="
     * + enemigo.getTipo());
     * } catch (Exception e) {
     * System.err.println("Error al cargar imagen de enemigo: " + imagen);
     * e.printStackTrace();
     * }
     * }
     * }
     * }
     */

    private void mover() {
        contenedorMapa.setFocusTraversable(true);
        contenedorMapa.requestFocus();
        contenedorMapa.setOnKeyPressed(this::moverProtagonista);
    }

    private void moverProtagonista(KeyEvent e) {
        System.out.println("Tecla pulsada: " + e.getCode());
        protagonista = Proveedor.getInstance().getProtagonista();
        int dx = 0, dy = 0;

        String img = "/com/rodasfiti/images/";

        switch (e.getCode()) {
            case W:
            case UP:
                dx = -1;
                img += "caballero_arriba.png";
                break;
            case S:
            case DOWN:
                dx = 1;
                img += "caballero_abajo.png";
                break;
            case A:
            case LEFT:
                dy = -1;
                img += "caballero_izquierda.png";
                break;
            case D:
            case RIGHT:
                dy = 1;
                img += "caballero_derecha.png";
                break;
            default:
                return;
        }

        if (protagonista.mover(dx, dy, escenario)) {

            posX = protagonista.getFila();
            posY = protagonista.getColumna();

            protagonistaView.setImage(cargarImagen(img));

            mainGrid.getChildren().remove(protagonistaView);
            mainGrid.add(protagonistaView, posY, posX);

            movimientosRestantes--;
            movimientosFaltantes.setText(String.valueOf(movimientosRestantes));
        }

        System.out.println("Intentando mover a (" + dx + ", " + dy + ")");
        System.out.println("Después de mover: (" + protagonista.getFila() + ", " + protagonista.getColumna() + ")");
    }

    /*
     * private void moverEnemigos() {
     * // Crear una copia de las posiciones actuales para controlar colisiones
     * ArrayList<String> posicionesOcupadas = new ArrayList<>();
     * 
     * for (Enemigo enemigo : listaEnemigos) {
     * int actualX = enemigo.getX();
     * int actualY = enemigo.getY();
     * posicionesOcupadas.add(actualX + "," + actualY); // Añadir la posición actual
     * 
     * int distanciaX = Math.abs(actualX - posX);
     * int distanciaY = Math.abs(actualY - posY);
     * 
     * int nuevaX = actualX;
     * int nuevaY = actualY;
     * 
     * if (distanciaX <= 2 && distanciaY <= 2) {
     * if (actualX < posX)
     * nuevaX++;
     * else if (actualX > posX)
     * nuevaX--;
     * if (actualY < posY)
     * nuevaY++;
     * else if (actualY > posY)
     * nuevaY--;
     * } else {
     * int direccion = r.nextInt(4);
     * switch (direccion) {
     * case 0:
     * nuevaX--;
     * break;
     * case 1:
     * nuevaX++;
     * break;
     * case 2:
     * nuevaY--;
     * break;
     * case 3:
     * nuevaY++;
     * break;
     * }
     * }
     * 
     * String nuevaPos = nuevaX + "," + nuevaY;
     * 
     * // Verifica que no se mueva a una posición ya ocupada ni al jugador
     * if (esPosicionValida(nuevaX, nuevaY) &&
     * !posicionesOcupadas.contains(nuevaPos)) {
     * enemigo.setX(nuevaX);
     * enemigo.setY(nuevaY);
     * posicionesOcupadas.add(nuevaPos); // Marcar como ocupada
     * }
     * }
     * }
     */

    private boolean puedeMoverA(int x, int y) {
        char[][] mapa = escenario.getMapa();
        if (x < 0 || x >= mapa.length || y < 0 || y >= mapa[0].length) {
            return false;
        }
        return mapa[x][y] == 'S'; // 'S' es suelo, por ejemplo
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