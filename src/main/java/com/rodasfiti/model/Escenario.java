package com.rodasfiti.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Escenario {
    private char[][] mapa;

    public Escenario(char[][] mapa) {
        this.mapa = mapa;
    }

    public char[][] getMapa() {
        return mapa;
    }

    public static Escenario cargarDesdeCSV(Path rutaCSV) {
        List<char[]> filas = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(rutaCSV)) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] tokens = linea.split(",");
                char[] fila = new char[tokens.length];
                for (int i = 0; i < tokens.length; i++) {
                    fila[i] = tokens[i].charAt(0);
                }
                filas.add(fila);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        char[][] mapa = filas.toArray(new char[0][]);
        return new Escenario(mapa);
    }
}
