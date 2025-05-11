package com.rodasfiti.model;

public class Protagonista extends Personaje {

    private int fila;
    private int columna;
    private int posicion;

    public Protagonista(String nombre, int vida, int ataque, int defensa, int posicion, int nivel, int velocidad) {
        super( vida, ataque, defensa, nivel, velocidad);
        this.posicion = posicion;
        this.nombre = nombre;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
    public void setFila(int fila) {
        this.fila = fila;
    }
    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getPosicion() {
        return this.posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public void mover(int nuevaFila, int nuevaColumna) {
        this.fila = nuevaFila;
        this.columna = nuevaColumna;
    }

    @Override
    public void atacar(Personaje objetivo) {
        super.atacar(objetivo);
    }

    @Override
    public void movimiento() {
        super.movimiento();
    }
}
