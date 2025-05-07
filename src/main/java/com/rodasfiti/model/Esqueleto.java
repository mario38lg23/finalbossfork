package com.rodasfiti.model;

public class Esqueleto extends Enemigo {

    public Esqueleto(String nombre, int vida, int ataque, int defensa, int percepcion, int velocidad) {
        super(nombre, vida, ataque, defensa, percepcion, velocidad);

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
