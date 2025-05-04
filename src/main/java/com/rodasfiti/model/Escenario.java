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
        try {
            BufferedReader reader = Files.newBufferedReader(rutaCSV);
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                char[] fila = new char[partes.length];
                for (int i = 0; i < partes.length; i++) {
                    fila[i] = partes[i].trim().charAt(0);
                }
                filas.add(fila);
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("Error leyendo el archivo CSV:");
            e.printStackTrace();
            return null;
        }
        char[][] mapa = new char[filas.size()][];
        for (int i = 0; i < filas.size(); i++) {
            mapa[i] = filas.get(i);
        }
        return new Escenario(mapa);
    }
}
