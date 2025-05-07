package com.rodasfiti.model;

public class Protagonista extends Personaje {

    public Protagonista(String nombre, int vida, int ataque, int defensa, int posicion, int nivel, int velocidad) {
        super(nombre, vida, ataque, defensa, posicion, nivel, velocidad);

    }

    @Override
    public void atacar() {
        super.atacar();
    }

    @Override
    public void movimiento() {
        super.movimiento();
    }

}
