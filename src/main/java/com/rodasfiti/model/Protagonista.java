package com.rodasfiti.model;

public class Protagonista extends Personaje {

    private int fila;
    private int columna;

    public Protagonista(String nombre, int vida, int ataque, int defensa, int posicion, int nivel, int velocidad) {
        super(nombre, vida, ataque, defensa, posicion, nivel, velocidad);
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public void mover(int nuevaFila, int nuevaColumna) {
        this.fila = nuevaFila;
        this.columna = nuevaColumna;
    }

    @Override
    public void atacar() {
        super.atacar();
    }

    @Override
    public void movimiento() {
        super.movimiento();
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> Mario
