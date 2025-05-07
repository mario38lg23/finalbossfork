package com.rodasfiti.model;

public abstract class Enemigo {
    protected String nombre;
    protected int vida;
    protected int ataque;
    protected int defensa;

    protected int percepcion;
    protected int velocidad;

    public Enemigo(String nombre, int vida, int ataque, int defensa, int percepcion, int velocidad) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;

        this.percepcion = percepcion;
        this.velocidad = velocidad;
    }

    public void atacar() {

    }

    public void movimiento() {

    }

}
