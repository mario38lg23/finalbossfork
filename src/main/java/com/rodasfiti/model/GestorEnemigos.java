package com.rodasfiti.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;


public class GestorEnemigos {

    public static ArrayList<Enemigo> cargarEnemigosDesdeCSV(String rutaCSV) {
    ArrayList<Enemigo> listaEnemigos = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(
            new InputStreamReader(Objects.requireNonNull(
                    GestorEnemigos.class.getResourceAsStream(rutaCSV))))) {

        String linea;
        boolean esPrimera = true;
        while ((linea = br.readLine()) != null) {
            if (esPrimera) {
                esPrimera = false;
                continue;
            }

            String[] partes = linea.split(",");
            if (partes.length == 9) {
                TipoEnemigo tipo = TipoEnemigo.valueOf(partes[0].toUpperCase().trim());
                int nivel = Integer.parseInt(partes[1]);
                int ataque = Integer.parseInt(partes[2]);
                int defensa = Integer.parseInt(partes[3]);
                int vida = Integer.parseInt(partes[4]);
                int velocidad = Integer.parseInt(partes[5]);
                int percepcion = Integer.parseInt(partes[6]);
                int x = Integer.parseInt(partes[7]);
                int y = Integer.parseInt(partes[8]);

                listaEnemigos.add(new Enemigo(tipo, nivel, ataque, defensa, vida, velocidad, percepcion, x, y));

            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return listaEnemigos;
}

}

