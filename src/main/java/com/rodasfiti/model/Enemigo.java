package com.rodasfiti.model;

public abstract class Enemigo extends Personaje{
    protected int percepcion;

    public Enemigo(String nombre, int vida, int ataque, int defensa, int posicion, int nivel, int velocidad, int percepcion) {
        super(nombre, vida, ataque, defensa, posicion, nivel, velocidad);
        this.percepcion = percepcion;
    }

    public void atacar() {

    }

    public void movimiento() {

    }

}
